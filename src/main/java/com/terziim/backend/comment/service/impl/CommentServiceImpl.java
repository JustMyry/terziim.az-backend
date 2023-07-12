package com.terziim.backend.comment.service.impl;


import com.terziim.backend.comment.dto.request.IcpCommentAddRequest;
import com.terziim.backend.comment.dto.response.IcpCommentResponse;
import com.terziim.backend.comment.mapper.CommentMapper;
import com.terziim.backend.comment.model.Comment;
import com.terziim.backend.comment.repository.CommentRepository;
import com.terziim.backend.comment.service.CommentService;
import com.terziim.backend.guard.service.GuardExternalService;
import com.terziim.backend.icpcommication.request.IcpJustJwt;
import com.terziim.backend.icpcommication.response.IcpResponseModel;
import com.terziim.backend.icpcommication.response.IcpResponseStatus;
import com.terziim.backend.notification.service.NotificationExternalService;
import com.terziim.backend.order.service.OrderExternalService;
import com.terziim.backend.security.jwt.JwtProvider;
import com.terziim.backend.user.model.AppUser;
import com.terziim.backend.user.service.UserExternalService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository repository;
    private final JwtProvider jwtProvider;
    private final UserExternalService userService;
    private final GuardExternalService guardService;
    private final OrderExternalService orderService;
    private final NotificationExternalService notification;
    public CommentServiceImpl(CommentRepository repository, JwtProvider jwtProvider, UserExternalService userService,
                              GuardExternalService guardService, OrderExternalService orderService, NotificationExternalService notification){
        this.repository = repository;
        this.userService = userService;
        this.jwtProvider = jwtProvider;
        this.guardService = guardService;
        this.orderService = orderService;
        this.notification = notification;
    }



    @Override
    public IcpResponseModel<String> makeComment(IcpCommentAddRequest payload) {
        AppUser user = userService.findAppUserByUserId(jwtProvider.getSubject(payload.getJwt()));
        if (user==null || !user.isNotLocked() || guardService.verifyRequestFromUnUsualWords(payload.getComment(), " "))
            /////
            ///// Burada Report List cagira bilersen
            /////
            return IcpResponseModel.<String>builder()
                    .response("Yanlis ve ya Terziim Istifadeci Sozlesmesini (T.I.S.) pozan istek.")
                    .status(IcpResponseStatus.getRequestIsInvalid())
                    .build();
        Boolean userBoughtProduct = orderService.getOrdersByBuyerIdAndProductIdWithOffset(user.getUserId(), payload.getProductId(), 0, 20).size() > 0;
        Comment comment = CommentMapper.INSTANCE.getCommentFromRequest(payload);
        comment.setSenderId(user.getUserId());
        comment.setUserBoughtProduct(userBoughtProduct);
        repository.save(comment);
        notification.createCommentNotification(user.getUserId(), comment.getComment(), comment.getProductId(), comment.getId());
        return IcpResponseModel.<String>builder()
                .response("Serh elave edildi.")
                .status(IcpResponseStatus.getSuccess())
                .build();
    }


    @Override
    public IcpResponseModel<String> deleteComment(IcpJustJwt payload, Long id) {
        AppUser user = userService.findAppUserByUserId(jwtProvider.getSubject(payload.getJwt()));
        Comment comment = repository.findCommentById(id);
        if (user==null || !user.isNotLocked() || comment==null || !comment.getSenderId().equals(user.getUserId()))
            return IcpResponseModel.<String>builder()
                    .response("Yanlis ve ya Terziim Istifadeci Sozlesmesini (T.I.S.) pozan istek.")
                    .status(IcpResponseStatus.getRequestIsInvalid())
                    .build();
        repository.delete(comment);
        return IcpResponseModel.<String>builder()
                .response("Sehr silindi.")
                .status(IcpResponseStatus.getSuccess())
                .build();
    }


    @Override
    public IcpResponseModel<List<IcpCommentResponse>> getCommentsOfProduct(Long id, int offset) {
        List<IcpCommentResponse> responses = repository.getCommentsOfProduct(id, offset, 20).stream().map(s->{
            IcpCommentResponse comment = CommentMapper.INSTANCE.getCommentResponse(s);
            AppUser user = userService.findAppUserByUserId(comment.getSenderId());
            comment.setSenderUsername(user.getUsername());
            comment.setSenderProfilePicture(user.getProfilePictureUrl());
            return comment;
        }).collect(Collectors.toList());
        return IcpResponseModel.<List<IcpCommentResponse>>builder()
                .response(responses)
                .status(IcpResponseStatus.getSuccess())
                .build();
    }


}

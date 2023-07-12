package com.terziim.backend.like.service.impl;


import com.terziim.backend.icpcommication.request.IcpJustJwt;
import com.terziim.backend.icpcommication.response.IcpResponseModel;
import com.terziim.backend.icpcommication.response.IcpResponseStatus;
import com.terziim.backend.like.dto.request.IcpLikeRequest;
import com.terziim.backend.like.dto.response.IcpLikeResponse;
import com.terziim.backend.like.mapper.LikeMapper;
import com.terziim.backend.like.model.Likes;
import com.terziim.backend.like.repository.LikeRepository;
import com.terziim.backend.like.service.LikeService;
import com.terziim.backend.product.model.Product;
import com.terziim.backend.product.service.ProductExternalService;
import com.terziim.backend.security.jwt.JwtProvider;
import com.terziim.backend.user.model.AppUser;
import com.terziim.backend.user.service.UserExternalService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LikeServiceImpl implements LikeService {

    private final LikeRepository repository;
    private final UserExternalService userService;
    private final JwtProvider jwtProvider;
    private final ProductExternalService productService;
    public LikeServiceImpl(LikeRepository repository,UserExternalService userService, JwtProvider jwtProvider, ProductExternalService productService){
        this.repository = repository;
        this.userService = userService;
        this.jwtProvider = jwtProvider;
        this.productService = productService;
    }



    @Override
    public IcpResponseModel<String> likeProducts(IcpLikeRequest payload) {
        AppUser user = userService.findAppUserByUserId(jwtProvider.getSubject(payload.getJwt()));
        Product product = productService.getProductById(payload.getProductId());
        Likes testLike = repository.getLikeByUserIdAndProduct(user.getUserId(), payload.getProductId());
        if(user==null || !user.isNotLocked() || product==null || !product.getNotLocked() || testLike!=null)
            return IcpResponseModel.<String>builder()
                    .response("Yanlis ve ya Terziim Istifadeci Sozlesmesini (T.I.S.) pozan istek.")
                    .status(IcpResponseStatus.getRequestIsInvalid())
                    .build();
        Likes like = Likes.builder().userId(user.getUserId()).productId(product.getId()).build();
        repository.save(like);
        // Istifadecinin beyendiyi mehsullarin sayi 70den artiq olarsa ilk elave olunani silir
        List<Likes> likesOfUser = repository.getAllLikesByUserId(user.getUserId());
        if(likesOfUser.size() >= 70)
            repository.delete(likesOfUser.get(likesOfUser.size()-1));
        return IcpResponseModel.<String>builder()
                .response("Mehsul beyenilenler listine elave edildi!")
                .status(IcpResponseStatus.getSuccess())
                .build();
    }


    @Override
    public IcpResponseModel<String> unLikeProducts(IcpJustJwt payload, Long id) {
        AppUser user = userService.findAppUserByUserId(jwtProvider.getSubject(payload.getJwt()));
        Likes like = repository.findLikesById(id);
        if(user==null || !user.isNotLocked() || like==null || !like.getUserId().equals(user.getUserId()))
            return IcpResponseModel.<String>builder()
                    .response("Yanlis ve ya Terziim Istifadeci Sozlesmesini (T.I.S.) pozan istek.")
                    .status(IcpResponseStatus.getRequestIsInvalid())
                    .build();
        repository.delete(like);
        return IcpResponseModel.<String>builder()
                .response("Mehsul beyenilenler listinden cixarildi")
                .status(IcpResponseStatus.getSuccess())
                .build();
    }


    @Override
    public IcpResponseModel<List<IcpLikeResponse>> likedProducts(IcpJustJwt payload, int offset) {
        AppUser user = userService.findAppUserByUserId(jwtProvider.getSubject(payload.getJwt()));
        if(user==null || !user.isNotLocked())
            return IcpResponseModel.<List<IcpLikeResponse>>builder()
                    .response(null)
                    .status(IcpResponseStatus.getRequestIsInvalid())
                    .build();
        List<IcpLikeResponse> responses = repository.getLikesByUserId(user.getUserId(), offset, 20).stream().map(s->{
            IcpLikeResponse like = LikeMapper.INSTANCE.getLikeResponse(productService.getProductById(s.getProductId()));
            like.setId(s.getId());
            return like;
        }).collect(Collectors.toList());
        return IcpResponseModel.<List<IcpLikeResponse>>builder()
                .response(responses)
                .status(IcpResponseStatus.getSuccess())
                .build();
    }

}

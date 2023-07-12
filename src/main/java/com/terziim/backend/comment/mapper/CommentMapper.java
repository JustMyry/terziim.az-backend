package com.terziim.backend.comment.mapper;


import com.terziim.backend.comment.dto.request.IcpCommentAddRequest;
import com.terziim.backend.comment.dto.response.IcpCommentResponse;
import com.terziim.backend.comment.model.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.factory.Mappers.getMapper;


@Mapper(componentModel = "spring")
public interface CommentMapper {

    CommentMapper INSTANCE = getMapper(CommentMapper.class);


    @Mapping(source = "id", target = "id")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "senderId", target = "senderId")
    @Mapping(source = "comment", target = "comment")
    @Mapping(source = "userBoughtProduct", target = "userBoughtProduct")
    IcpCommentResponse getCommentResponse(Comment comment);



    @Deprecated
    // Lazimsiz metoddur. Cunki bir cox parametri elave etmek ucun mutleq manual qurmaya ehtiyac olur.
    @Mapping(target = "isActive", expression = "java(true)")
    Comment getCommentFromRequest(IcpCommentAddRequest request);


}

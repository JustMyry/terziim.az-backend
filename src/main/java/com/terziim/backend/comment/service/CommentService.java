package com.terziim.backend.comment.service;

import com.terziim.backend.comment.dto.request.IcpCommentAddRequest;
import com.terziim.backend.comment.dto.response.IcpCommentResponse;
import com.terziim.backend.icpcommication.request.IcpJustJwt;
import com.terziim.backend.icpcommication.response.IcpResponseModel;

import java.util.List;

public interface CommentService {


    IcpResponseModel<String> makeComment(IcpCommentAddRequest payload);

    IcpResponseModel<String> deleteComment(IcpJustJwt payload, Long id);

    IcpResponseModel<List<IcpCommentResponse>> getCommentsOfProduct(Long id, int offset);


}

package com.terziim.backend.comment.resource;


import com.terziim.backend.comment.dto.request.IcpCommentAddRequest;
import com.terziim.backend.comment.dto.response.IcpCommentResponse;
import com.terziim.backend.comment.service.CommentService;
import com.terziim.backend.icpcommication.request.IcpJustJwt;
import com.terziim.backend.icpcommication.response.IcpResponseModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/serh")
public class CommentResource {

    private final CommentService service;
    public CommentResource(CommentService service){
        this.service = service;
    }


    @PostMapping("/elaveet")
    public IcpResponseModel<String> makeComment(@RequestBody IcpCommentAddRequest payload){
        return service.makeComment(payload);
    }


    @PostMapping("/sil")
    public IcpResponseModel<String> deleteComment(@RequestBody IcpJustJwt payload, @RequestParam("id") Long id){
        return service.deleteComment(payload, id);
    }


    @GetMapping("/mehsul")
    public IcpResponseModel<List<IcpCommentResponse>> getCommentsOfProduct(@RequestParam("id") Long id, @RequestParam("offset") int offset){
        return service.getCommentsOfProduct(id, offset);
    }



}

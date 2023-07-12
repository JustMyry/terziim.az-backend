package com.terziim.backend.comment.service;

import com.terziim.backend.comment.model.Comment;

import java.util.List;

public interface CommentExternalService {

    void saveComment(Comment comment);
    void deleteComment(Long id);

    Comment getCommentById(Long id);

    List<Comment> getCommentsBySenderId(String id);


    void deActiveComment(Long id);
}

package com.terziim.backend.comment.service.impl;


import com.terziim.backend.comment.model.Comment;
import com.terziim.backend.comment.repository.CommentRepository;
import com.terziim.backend.comment.service.CommentExternalService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentExternalServiceImpl implements CommentExternalService {

    private final CommentRepository repository;
    public CommentExternalServiceImpl(CommentRepository repository){
        this.repository = repository;
    }


    @Override
    public void saveComment(Comment comment) {
        repository.save(comment);
    }

    @Override
    public void deleteComment(Long id) {
        repository.delete(repository.findCommentById(id));
    }

    @Override
    public Comment getCommentById(Long id) {
        return repository.findCommentById(id);
    }

    @Override
    public List<Comment> getCommentsBySenderId(String id) {
        return repository.getCommentsOfUser(id, 0, 200);
    }

    @Override
    public void deActiveComment(Long id) {
        Comment comment = repository.findCommentById(id);
        comment.setActive(false);
        repository.save(comment);

    }
}

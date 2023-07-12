package com.terziim.backend.notification.model;

import com.terziim.backend.icpmodel.BaseModel;
import jakarta.persistence.Entity;
import lombok.Builder;

import java.util.Date;


@Entity
@Builder
public class CommentNotification extends BaseModel {

    private String userId;

    private Long commentId;
    private String comment;
    private boolean seen;

    private Long productId;


    //Constructors
    public CommentNotification() {}

    public CommentNotification(Long id, Date createdAt, Date updatedAt, String userId, Long commentId, String comment, boolean seen, Long productId) {
        super(id, createdAt, updatedAt);
        this.userId = userId;
        this.commentId = commentId;
        this.comment = comment;
        this.seen = seen;
        this.productId = productId;
    }

    public CommentNotification(String userId, Long commentId, String comment, boolean seen, Long productId) {
        this.userId = userId;
        this.commentId = commentId;
        this.comment = comment;
        this.seen = seen;
        this.productId = productId;
    }

    //Getters and Setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }
}

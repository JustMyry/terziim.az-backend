package com.terziim.backend.comment.repository;

import com.terziim.backend.comment.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Comment findCommentById(Long id);

    @Query(value = "SELECT * FROM comment u WHERE u.is_active = true AND u.product_id=:productId ORDER BY id DESC  limit :limit offset :offset ", nativeQuery = true)
    List<Comment> getCommentsOfProduct(@Param("productId") Long productId, @Param("offset") int offset, @Param("limit") int limit);

    @Query(value = "SELECT * FROM comment u WHERE u.sender_id=:senderId  ORDER BY id DESC limit :limit offset :offset", nativeQuery = true)
    List<Comment> getCommentsOfUser(@Param("senderId") String senderId, @Param("offset") int offset, @Param("limit") int limit);

}

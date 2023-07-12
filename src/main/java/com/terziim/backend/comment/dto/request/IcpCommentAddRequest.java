package com.terziim.backend.comment.dto.request;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IcpCommentAddRequest {

    private String jwt;

    private Long productId;

    private String comment;

}

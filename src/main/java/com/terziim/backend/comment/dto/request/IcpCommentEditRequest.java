package com.terziim.backend.comment.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IcpCommentEditRequest {

    private String jwt;

    private Long commentId;

    private String comment;
}

package com.terziim.backend.comment.dto.response;


import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IcpCommentResponse {

    private Long id;

    private String senderId;
    private String senderUsername;
    private String senderProfilePicture;

    private String comment;
    private Boolean userBoughtProduct;

    private LocalDate createdAt;



}

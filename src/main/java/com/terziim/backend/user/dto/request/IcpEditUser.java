package com.terziim.backend.user.dto.request;

import lombok.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class IcpEditUser {

    private String jwt;

    @NonNull
    private String username;
    @NonNull
    private String email;


    private String bio;
    private String profilePictureUrl;
    private String privacy;
    private String phone;
    private String address;
    private String gender;

}

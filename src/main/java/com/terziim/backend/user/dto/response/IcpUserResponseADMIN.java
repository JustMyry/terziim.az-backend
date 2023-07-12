package com.terziim.backend.user.dto.response;

import com.terziim.backend.authority.model.Authority;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IcpUserResponseADMIN {

    private Long id;
    private Date createdAt;
    private Date updatedAt;

    private String userId;
    private String username;
    private String email;
    private String password;

    private String verificationCode;

    private String userType;

    private String bio;
    private String profilePictureUrl;
    private String privacy;
    private String phone;
    private String address;
    private String gender;

    private Float star;
    private Integer howManyUserStared;

    private Date lastLoginDate;
    private boolean isActive;
    private boolean isNotLocked;

    private String role;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            joinColumns = @JoinColumn(name = "appuser_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id")
    )
    private Collection<Authority> authorities = new ArrayList<>();

}

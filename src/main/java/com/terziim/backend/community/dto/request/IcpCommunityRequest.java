package com.terziim.backend.community.dto.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IcpCommunityRequest {

    private String jwt;
    private String community;

}

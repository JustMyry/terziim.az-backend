package com.terziim.backend.star.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IcpStarRequest {

    private String jwt;
    private Float star;

}

package com.terziim.backend.community.dto.response;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class IcpCommunityResponse {

    private Long id;
    private Date createdAt;
    private String community;

}

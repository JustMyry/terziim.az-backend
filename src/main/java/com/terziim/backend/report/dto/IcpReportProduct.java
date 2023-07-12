package com.terziim.backend.report.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IcpReportProduct {

    private String jwt;
    private Long whatHasBeenReported;
    private String reportReason;

}

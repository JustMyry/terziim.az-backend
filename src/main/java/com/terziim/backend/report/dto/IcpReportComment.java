package com.terziim.backend.report.dto;

import lombok.*;


@Getter
@Setter
public class IcpReportComment {

    private String jwt;
    private Long whatHasBeenReported;
    private String reportReason;

}
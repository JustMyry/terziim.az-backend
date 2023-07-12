package com.terziim.backend.report.dto;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IcpReportUser {

    private String jwt;
    private String whoHasBeenReported;
    private String reportReason;

}

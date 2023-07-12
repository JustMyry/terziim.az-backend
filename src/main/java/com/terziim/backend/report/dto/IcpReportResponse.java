package com.terziim.backend.report.dto;


import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IcpReportResponse {

    private Long id;

    private String whoHasBeenReported;
    private String whoReported;
    private String type;

    private Boolean isActive;

    private String reportReason;

    private String actionReason;

    private LocalDate createdAt;

}


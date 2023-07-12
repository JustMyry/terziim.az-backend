package com.terziim.backend.report.mapper;


import com.terziim.backend.report.dto.*;
import com.terziim.backend.report.model.Report;
import com.terziim.backend.report.model.ReportAction;
import com.terziim.backend.report.model.ReportList;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper(componentModel = "spring")
public interface ReportMapper {

    ReportMapper INSTANCE = getMapper(ReportMapper.class);

    ReportAction getActionFromReport(ReportList list);


    Report getReportFromComRequest(IcpReportComment comment);

    Report getReportFromProdRequest(IcpReportProduct product);

    Report getReportFromQuesRequest(IcpReportQuestion question);

    Report getReportFromUserRequest(IcpReportUser user);


    @Mapping(source = "id", target = "id")
    IcpReportResponse getReportResponse(Report report);

}

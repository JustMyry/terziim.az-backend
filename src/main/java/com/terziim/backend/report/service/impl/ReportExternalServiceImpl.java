package com.terziim.backend.report.service.impl;


import com.terziim.backend.icpcommication.response.IcpResponseModel;
import com.terziim.backend.icpcommication.response.IcpResponseStatus;
import com.terziim.backend.report.dto.IcpReportResponse;
import com.terziim.backend.report.mapper.ReportMapper;
import com.terziim.backend.report.model.Report;
import com.terziim.backend.report.repository.ReportRepository;
import com.terziim.backend.report.service.ReportExternalService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportExternalServiceImpl implements ReportExternalService {

    private final ReportRepository repository;
    public ReportExternalServiceImpl(ReportRepository repository) {
        this.repository = repository;
    }

    @Override
    public IcpResponseModel<IcpReportResponse> getReportById(Long id) {
        IcpReportResponse response = ReportMapper.INSTANCE.getReportResponse(repository.findReportFromId(id));
        return IcpResponseModel.<IcpReportResponse>builder()
                .response(response)
                .status(IcpResponseStatus.getSuccess())
                .build();
    }

    @Override
    public IcpResponseModel<List<IcpReportResponse>> getAllReportsByDate(String gun) {
        List<IcpReportResponse> response = repository.findReportActionsByDate(LocalDate.parse(gun)).stream().map(s->{
            return ReportMapper.INSTANCE.getReportResponse(s);
        }).collect(Collectors.toList());
        return IcpResponseModel.<List<IcpReportResponse>>builder()
                .response(response)
                .status(IcpResponseStatus.getSuccess())
                .build();
    }

    @Override
    public IcpResponseModel<List<IcpReportResponse>> getAllReportsByDateAndType(String gun, String type) {
        List<IcpReportResponse> response = repository.findReportActionsByDateAndType(LocalDate.parse(gun), type).stream().map(s->{
            return ReportMapper.INSTANCE.getReportResponse(s);
        }).collect(Collectors.toList());
        return IcpResponseModel.<List<IcpReportResponse>>builder()
                .response(response)
                .status(IcpResponseStatus.getSuccess())
                .build();
    }

    @Override
    public IcpResponseModel<List<IcpReportResponse>> getReportFromWhoReportedAndType(String category, String userId) {
        List<IcpReportResponse> response = repository.findReportFromWhoReportedAndType(userId, category).stream().map(s->{
            return ReportMapper.INSTANCE.getReportResponse(s);
        }).collect(Collectors.toList());
        return IcpResponseModel.<List<IcpReportResponse>>builder()
                .response(response)
                .status(IcpResponseStatus.getSuccess())
                .build();
    }

    @Override
    public IcpResponseModel<List<IcpReportResponse>> getReportFromWhoHasBeenReportedAndType(String category, String userId) {
            List<IcpReportResponse> response = repository.findReportFromReportedIdAndType(userId, category).stream().map(s->{
                return ReportMapper.INSTANCE.getReportResponse(s);
            }).collect(Collectors.toList());
            return IcpResponseModel.<List<IcpReportResponse>>builder()
                    .response(response)
                    .status(IcpResponseStatus.getSuccess())
                    .build();
    }

    @Override
    public IcpResponseModel<String> makeReportSeen(Long reportId) {
        Report report = repository.findReportFromId(reportId);
        report.setActive(false);
        repository.save(report);
        return IcpResponseModel.<String>builder()
                .response("Successful")
                .status(IcpResponseStatus.getSuccess())
                .build();
    }
}

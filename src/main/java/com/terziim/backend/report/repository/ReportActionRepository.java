package com.terziim.backend.report.repository;


import com.terziim.backend.report.model.ReportAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReportActionRepository extends JpaRepository<ReportAction, Long> {


    @Query(value = "SELECT * FROM report_action u WHERE u.who_has_been_reported=:whoHasBeenReported AND u.type=:type AND u.is_active = true ",
            nativeQuery = true)
    List<ReportAction> findReportActionFromReportedIdAndType(@Param("whoHasBeenReported") String whoHasBeenReported, @Param("type") String type);


    @Query(value = "SELECT * FROM report_action u WHERE u.id=:id AND u.is_active = true ",
            nativeQuery = true)
    ReportAction findReportActionFromId(@Param("id") Long id);

    @Query(value = "SELECT * FROM report_action u WHERE u.created_at=:date AND u.is_active = true", nativeQuery = true)
    List<ReportAction> findReportActionsByDate(@Param("date") LocalDate date);

//    @Query(value = "SELECT * FROM report_list u WHERE u.who_has_been_reported=:userId AND u.is_active = true", nativeQuery = true)
//    List<ReportAction> findReportListsByWhoHasBeenReported(@Param("userId") String userid);



}

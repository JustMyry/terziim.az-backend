package com.terziim.backend.report.repository;


import com.terziim.backend.report.model.ReportList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface ReportListRepository extends JpaRepository<ReportList, Long> {


    ReportList findReportListByWhoHasBeenReportedAndWhoReported(String whoHasBeenReported, String whoReported);

    ReportList findReportListById(Long id);


    @Query(value = "SELECT * FROM report_list u WHERE u.who_has_been_reported=:whoHasBeenReported AND u.who_reported=:whoReported AND u.is_active = true ",
            nativeQuery = true)
    List<ReportList> findReportListsWithUsersAndActive(@Param("whoHasBeenReported") String whoHasBeenReported, @Param("whoReported") String whoReported);


    @Query(value = "SELECT * FROM report_list u WHERE u.who_has_been_reported=:userId AND u.is_active = true", nativeQuery = true)
    List<ReportList> findReportListsByWhoHasBeenReported(@Param("userId") String userid);

    @Query(value = "SELECT * FROM report_list u WHERE u.created_at=:date AND u.is_active = true", nativeQuery = true)
    List<ReportList> findReportListsByDate(@Param("date") LocalDate date);


    @Query(value = "SELECT * FROM report_list u WHERE u.who_has_been_reported=:id AND u.type=:type AND u.is_active = true", nativeQuery = true)
    List<ReportList> findReportListsByIdAndType(@Param("id") String id, @Param("type") String type);
}

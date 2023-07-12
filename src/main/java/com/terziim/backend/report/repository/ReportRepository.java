package com.terziim.backend.report.repository;


import com.terziim.backend.report.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {


    @Query(value = "SELECT * FROM report u WHERE u.id=:id", nativeQuery = true)
    Report findReportFromId(@Param("id") Long id);


    @Query(value = "SELECT * FROM report u WHERE u.who_has_been_reported=:whoHasBeenReported AND u.type=:type AND u.is_active = true ORDER BY id DESC ",
            nativeQuery = true)
    List<Report> findReportFromReportedIdAndType(@Param("whoHasBeenReported") String whoHasBeenReported, @Param("type") String type);


    @Query(value = "SELECT * FROM report u WHERE u.who_reported=:whoReported AND u.type=:type AND u.is_active = true ORDER BY id DESC ",
            nativeQuery = true)
    List<Report> findReportFromWhoReportedAndType(@Param("whoReported") String whoReported, @Param("type") String type);



    @Query(value =
            "SELECT * FROM report u WHERE u.who_has_been_reported=:whoHasBeenReported AND u.who_reported=:whoReported AND u.type=:type AND u.is_active = true ORDER BY id DESC ",
            nativeQuery = true)
    Report findReportFromWhoReportedAndWhoHasBeenReportedAndType(@Param("whoReported") String whoReported, @Param("type") String type,
                                                                       @Param("whoHasBeenReported") String whoHasBeenReported);

    @Query(value =
            "SELECT * FROM report u WHERE u.what_has_been_reported=:whatHasBeenReported AND u.who_reported=:whoReported AND u.type=:type AND u.is_active = true ORDER BY id DESC ",
            nativeQuery = true)
    Report findReportFromWhoReportedAndWhatHasBeenReportedAndType(@Param("whoReported") String whoReported, @Param("type") String type,
                                                                 @Param("whatHasBeenReported") String whatHasBeenReported);



    @Query(value = "SELECT * FROM report u WHERE u.created_at=:date AND u.is_active = true ORDER BY id DESC ", nativeQuery = true)
    List<Report> findReportActionsByDate(@Param("date") LocalDate date);


    @Query(value = "SELECT * FROM report u WHERE u.created_at=:date AND u.is_active = true AND u.type=:type ORDER BY id DESC ", nativeQuery = true)
    List<Report> findReportActionsByDateAndType(@Param("date") LocalDate date, @Param("type") String type);
}

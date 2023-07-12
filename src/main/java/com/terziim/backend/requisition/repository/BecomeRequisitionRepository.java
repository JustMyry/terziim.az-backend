package com.terziim.backend.requisition.repository;

import com.terziim.backend.requisition.model.BecomeRequisition;
import com.terziim.backend.requisition.model.Requisition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BecomeRequisitionRepository extends JpaRepository<BecomeRequisition, Long> {

    BecomeRequisition findBecomeRequisitionBySenderId(String senderId);

    BecomeRequisition findBecomeRequisitionById(Long id);


    @Query(value = "SELECT * FROM become_requisition u WHERE date(u.created_at) =:date AND u.is_active =true ORDER BY id DESC limit :limit offset :offset", nativeQuery = true)
    public List<BecomeRequisition> findBecomeRequisitionByDateWithOffset(@Param("date") String date, @Param("offset") int offset, @Param("limit") int limit);

    @Query(value = "SELECT * FROM become_requisition u WHERE u.is_active =true ORDER BY id DESC limit :limit offset :offset", nativeQuery = true)
    public List<BecomeRequisition> findBecomeRequisitionByActive(@Param("offset") int offset, @Param("limit") int limit);

}

package com.terziim.backend.requisition.repository;


import com.terziim.backend.requisition.model.Requisition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequisitionRepository extends JpaRepository<Requisition, Long> {

//
//    @Query(value = "SELECT * FROM requisition u WHERE u.created_at=:date AND u.is_active =true limit :limit offset :offset ", nativeQuery = true)
//    public List<Requisition> getRequisitionByCreatedAtWithOffset(@Param("date") LocalDate date, @Param("offset") int offset, @Param("limit") int limit);

    @Query(value = "SELECT * FROM requisition u WHERE u.id =:id", nativeQuery = true)
    public Requisition findRequisitionById(@Param("id") Long id);

    @Query(value = "SELECT * FROM requisition u WHERE u.requisition_category_id =:requisitionCategoryId AND u.is_active = true ORDER BY id DESC limit :limit offset :offset", nativeQuery = true)
    public List<Requisition> findRequisitionsByType(@Param("requisitionCategoryId") int requisitionCategoryId, @Param("offset") int offset, @Param("limit") int limit);

    @Query(value = "SELECT * FROM requisition u WHERE date(u.created_at) =:date AND u.is_active =true ORDER BY id DESC limit :limit offset :offset", nativeQuery = true)
    public List<Requisition> findRequisitionByCreatedAtWithOffset(@Param("date") String date, @Param("offset") int offset, @Param("limit") int limit);

    @Query(value = "SELECT * FROM requisition u WHERE u.sender_id =:senderId ORDER BY id DESC limit :limit offset :offset", nativeQuery = true)
    public List<Requisition> findRequisitionsBySenderIdWithOffset(@Param("senderId") String senderId, @Param("offset") int offset, @Param("limit") int limit);

    @Query(value = "SELECT * FROM requisition u WHERE u.requisition_category_id =:id AND u.is_active =true ORDER BY id DESC  limit :limit offset :offset", nativeQuery = true)
    public List<Requisition> findRequisitionsByCategoryWithOffset(@Param("id") int id, @Param("offset") int offset, @Param("limit") int limit);

    @Query(value = "SELECT * FROM requisition u WHERE u.is_active =true ORDER BY id DESC limit :limit offset :offset", nativeQuery = true)
    public List<Requisition> findAllByActiveWithOffset(@Param("offset") int offset, @Param("limit") int limit);

    @Query(value = "SELECT * FROM requisition ORDER BY id DESC limit :limit offset :offset", nativeQuery = true)
    public List<Requisition> findAllWithOffset(@Param("offset") int offset, @Param("limit") int limit);


    @Query(value = "SELECT * FROM requisition u WHERE u.requisition_category_id =:requisitionCategoryId AND u.sender_id =:senderId AND u.is_active = true ", nativeQuery = true)
    public Requisition findRequisitionBySenderAndType(@Param("senderId") String senderId, @Param("requisitionCategoryId") int requisitionCategoryId);

}

package com.terziim.backend.authority.repository;


import com.terziim.backend.authority.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<Authority, Long> {

//    @Query(
//            value = "SELECT u FROM Authority WHERE u.authority=:authorityName",
//            nativeQuery = true)
//    Authority findByName(String authorityName);
//
//    @Query(
//            value = "SELECT u FROM Authority WHERE u.id=:authorityId",
//            nativeQuery = true)
//    Authority findById(int authorityId);

    Authority findByAuthority(String authority);
    Authority findById(int id);

}

package com.terziim.backend.user.repository;

import com.terziim.backend.user.model.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {


    @Query(
            value = "SELECT * FROM AppUser ORDER BY id",
            countQuery = "SELECT count(*) FROM Users",
            nativeQuery = true)
    Page<AppUser> findAllUsersWithPagination(Pageable pageable);


    @Query(value = "SELECT * FROM app_user u WHERE u.username=:username", nativeQuery = true)
    AppUser findUserFromLogin(@Param("username") String username);


    @Query(value = "SELECT * FROM app_user u WHERE u.is_active = true AND u.banned=false AND u.user_id=:userId", nativeQuery = true)
    AppUser findAppUserByUserId(@Param("userId") String userId);


    @Query(value = "SELECT * FROM app_user u WHERE u.is_active = true AND u.banned=false AND u.id=:id", nativeQuery = true)
    AppUser findAppUserById(@Param("id") Long id);


    @Query(value = "SELECT * FROM app_user u WHERE u.is_active = true AND u.banned=false AND u.username=:username", nativeQuery = true)
    AppUser findAppUserByUsername(@Param("username") String username);

    @Query(value = "SELECT * FROM app_user u WHERE u.banned=false AND u.username=:username", nativeQuery = true)
    AppUser findAppUserByUsernameActiveFALSE(@Param("username") String username);


    @Query(value = "SELECT * FROM app_user u WHERE u.is_active = true AND u.banned=false AND u.email=:email", nativeQuery = true)
    AppUser findAppUserByEmail(@Param("email") String email);

    @Query(value = "SELECT * FROM app_user u WHERE u.is_active = true AND u.banned=false AND u.phone=:phone", nativeQuery = true)
    AppUser findAppUserByPhone(@Param("phone") String phone);

    @Query(value = "SELECT * FROM app_user u WHERE u.banned=false AND u.phone=:phone", nativeQuery = true)
    AppUser findAppUserByPhoneActiveFALSE(@Param("phone") String phone);


    @Query(value = "SELECT * FROM app_user u WHERE u.is_active = true AND u.banned=false AND u.in_not_locked = true AND u.username LIKE CONCAT('%',:userName,'%') ORDER BY id DESC ", nativeQuery = true)
    List<AppUser> findAppUserLikeUsername(@Param("userName") String userName);


}
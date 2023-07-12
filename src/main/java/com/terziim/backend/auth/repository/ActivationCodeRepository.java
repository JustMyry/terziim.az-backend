package com.terziim.backend.auth.repository;


import com.terziim.backend.auth.model.ActivationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivationCodeRepository extends JpaRepository<ActivationCode, Long> {

    ActivationCode findActivationCodeByUserName(String username);

}

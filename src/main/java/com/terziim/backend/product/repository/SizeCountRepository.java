package com.terziim.backend.product.repository;


import com.terziim.backend.product.model.SizeCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeCountRepository extends JpaRepository<SizeCount, Long> {

    SizeCount findSizeCountBySize(String size);


}

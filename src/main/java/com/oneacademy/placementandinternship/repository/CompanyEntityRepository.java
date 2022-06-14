package com.oneacademy.placementandinternship.repository;

import com.oneacademy.placementandinternship.entity.CompanyEntity;
import com.oneacademy.placementandinternship.model.CompanyModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyEntityRepository extends JpaRepository<CompanyEntity, Long> {

    boolean existsByCompanyName(String companyName);

    boolean existsByJobId(Long jobId);

    CompanyEntity findByJobId(Long jobId);

//    @Modifying
//    @Query("update company_entity c set")
//    void updateWithoutNull(@Param(value = "id"))

}
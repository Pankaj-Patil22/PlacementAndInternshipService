package com.oneacademy.placementandinternship.repository;

import com.oneacademy.placementandinternship.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyEntityRepository extends JpaRepository<CompanyEntity, Long> {

    boolean existsByCompanyName(String companyName);

    boolean existsByJobId(Long jobId);

    CompanyEntity findByJobId(Long jobId);

}
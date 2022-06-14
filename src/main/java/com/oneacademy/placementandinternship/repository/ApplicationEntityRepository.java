package com.oneacademy.placementandinternship.repository;

import com.oneacademy.placementandinternship.entity.ApplicationEntity;
import com.oneacademy.placementandinternship.model.ApplicationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationEntityRepository extends JpaRepository<ApplicationEntity, Long> {

    boolean existsByJobIdAndStudentId(Long jobId,Long studentId);

    List<ApplicationEntity> findAllByJobId(long jobId);

    ApplicationEntity findByStudentIdAndJobId(long studentId, long jobId);


}
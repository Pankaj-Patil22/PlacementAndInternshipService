package com.oneacademy.placementandinternship.repository;

import com.oneacademy.placementandinternship.entity.PlacedStudentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlacedStudentsEntityRepository extends JpaRepository<PlacedStudentsEntity, Long> {
    boolean existsByStudentId(long studentId);

    PlacedStudentsEntity findByStudentId(long studentId);

}
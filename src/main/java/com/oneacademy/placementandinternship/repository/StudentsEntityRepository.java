package com.oneacademy.placementandinternship.repository;

import com.oneacademy.placementandinternship.entity.StudentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentsEntityRepository extends JpaRepository<StudentsEntity, Long> {
    StudentsEntity findByStudentId(long studentId);

    boolean existsByStudentId(long studentId);
}
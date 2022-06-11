package com.oneacademy.placementandinternship.service;


import com.oneacademy.placementandinternship.entity.CompanyEntity;
import com.oneacademy.placementandinternship.entity.PlacedStudentsEntity;
import com.oneacademy.placementandinternship.model.CompanyModel;
import com.oneacademy.placementandinternship.model.PlacedStudentsModel;

import java.util.List;

public interface PlacementService {

    CompanyEntity newPlacement(CompanyModel companyModel);

    CompanyEntity deletePlacement(Long jobId);

    List<CompanyEntity> getAllPlacements();

    PlacedStudentsEntity addPlacedStudent(PlacedStudentsModel placedStudentsModel);

    PlacedStudentsEntity deletePlacedStudent(long studentId);

    List<PlacedStudentsEntity> getAllPlacedStudents();
}

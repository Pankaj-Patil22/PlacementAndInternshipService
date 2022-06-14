package com.oneacademy.placementandinternship.service;


import com.oneacademy.placementandinternship.entity.ApplicationEntity;
import com.oneacademy.placementandinternship.entity.CompanyEntity;
import com.oneacademy.placementandinternship.entity.PlacedStudentsEntity;
import com.oneacademy.placementandinternship.model.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public interface PlacementService {

    CompanyEntity newPlacement(CompanyCreationModel companyCreationModel);

    CompanyEntity deletePlacement(Long jobId);

    List<CompanyEntity> getAllPlacements();

    PlacedStudentsEntity addPlacedStudent(PlacedStudentsModel placedStudentsModel);

    PlacedStudentsEntity deletePlacedStudent(long studentId);

    List<PlacedStudentsEntity> getAllPlacedStudents();

    ApplicationEntity applyForPlacements(ApplicationModel applicationModel);

    CompanyEntity updatePlacement(CompanyUpdateModel companyUpdateModel);

    List<ApplicationEntity> getAllApplications(long jobId);

    List<ApplicationEntity> updateApplications(List<ApplicationModel> applicationModels);
}

package com.oneacademy.placementandinternship.service;


import com.oneacademy.placementandinternship.entity.CompanyEntity;
import com.oneacademy.placementandinternship.model.CompanyModel;

import java.util.List;

public interface PlacementService {

    CompanyEntity newPlacement(CompanyModel companyModel);

    CompanyEntity deletePlacement(Long jobId);

    List<CompanyEntity> getAllPlacements();
}

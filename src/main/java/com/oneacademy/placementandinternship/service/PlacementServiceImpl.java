package com.oneacademy.placementandinternship.service;

import com.oneacademy.placementandinternship.entity.CompanyEntity;
import com.oneacademy.placementandinternship.model.CompanyModel;
import com.oneacademy.placementandinternship.repository.CompanyEntityRepository;
import org.hibernate.id.UUIDGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PlacementServiceImpl implements PlacementService{

    private CompanyEntityRepository companyEntityRepository;

    public PlacementServiceImpl(CompanyEntityRepository companyEntityRepository) {
        this.companyEntityRepository = companyEntityRepository;
    }

    @Override
    public CompanyEntity newPlacement(CompanyModel companyModel) {

        if(companyEntityRepository.existsByCompanyName(companyModel.getCompanyName()))
        {
            throw new IllegalArgumentException("Company Already exists");
        }

        CompanyEntity companyEntity= new CompanyEntity();
        BeanUtils.copyProperties(companyModel,companyEntity);

        companyEntityRepository.save(companyEntity);

        return companyEntity;
    }

    @Override
    public CompanyEntity deletePlacement(Long jobId) {
        if(!companyEntityRepository.existsByJobId(jobId)){
            throw new IllegalArgumentException("Job opportunity doesn't exists");
        }

        CompanyEntity companyEntity= new CompanyEntity();

        BeanUtils.copyProperties(companyEntityRepository.findByJobId(jobId),companyEntity);

        companyEntityRepository.delete(companyEntity);

        return companyEntity;
    }

    @Override
    public List<CompanyEntity> getAllPlacements() {

        List<CompanyEntity> companyEntities= new ArrayList  <CompanyEntity>();
        companyEntityRepository.findAll().forEach(companyEntity -> companyEntities.add(companyEntity));

        return companyEntities;
    }
}

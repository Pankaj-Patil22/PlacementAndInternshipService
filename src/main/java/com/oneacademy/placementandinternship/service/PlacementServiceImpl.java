package com.oneacademy.placementandinternship.service;

import com.oneacademy.placementandinternship.entity.CompanyEntity;
import com.oneacademy.placementandinternship.entity.PlacedStudentsEntity;
import com.oneacademy.placementandinternship.model.CompanyModel;
import com.oneacademy.placementandinternship.model.PlacedStudentsModel;
import com.oneacademy.placementandinternship.repository.CompanyEntityRepository;
import com.oneacademy.placementandinternship.repository.PlacedStudentsEntityRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlacementServiceImpl implements PlacementService{

    private CompanyEntityRepository companyEntityRepository;
    private PlacedStudentsEntityRepository placedStudentsEntityRepository;

    public PlacementServiceImpl(CompanyEntityRepository companyEntityRepository, PlacedStudentsEntityRepository placedStudentsEntityRepository) {
        this.companyEntityRepository = companyEntityRepository;
        this.placedStudentsEntityRepository=placedStudentsEntityRepository;
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

        companyEntityRepository.delete(companyEntityRepository.findByJobId(jobId));

        return companyEntityRepository.findByJobId(jobId);
    }

    @Override
    public List<CompanyEntity> getAllPlacements() {

        return companyEntityRepository.findAll();
    }

    @Override
    public PlacedStudentsEntity addPlacedStudent(PlacedStudentsModel placedStudentsModel) {

    PlacedStudentsEntity placedStudentsEntity=new PlacedStudentsEntity();
    BeanUtils.copyProperties(placedStudentsModel,placedStudentsEntity);

    placedStudentsEntityRepository.save(placedStudentsEntity);

    return placedStudentsEntity;
    }

    @Override
    public PlacedStudentsEntity deletePlacedStudent(long studentId) {

        if(!placedStudentsEntityRepository.existsByStudentId(studentId)){
            throw new IllegalArgumentException("No placed student found with id= "+studentId);
        }

        placedStudentsEntityRepository.delete(placedStudentsEntityRepository.findByStudentId(studentId));

        return placedStudentsEntityRepository.findByStudentId(studentId);
    }

    @Override
    public List<PlacedStudentsEntity> getAllPlacedStudents() {

        return placedStudentsEntityRepository.findAll();
    }
}

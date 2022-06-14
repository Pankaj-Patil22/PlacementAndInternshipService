package com.oneacademy.placementandinternship.service;


import com.oneacademy.placementandinternship.entity.ApplicationEntity;
import com.oneacademy.placementandinternship.entity.CompanyEntity;
import com.oneacademy.placementandinternship.entity.PlacedStudentsEntity;
import com.oneacademy.placementandinternship.model.*;
import com.oneacademy.placementandinternship.repository.ApplicationEntityRepository;
import com.oneacademy.placementandinternship.repository.CompanyEntityRepository;
import com.oneacademy.placementandinternship.repository.PlacedStudentsEntityRepository;
import com.oneacademy.placementandinternship.repository.StudentsEntityRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;

@Service
public class PlacementServiceImpl implements PlacementService{


    @Autowired
    private StudentsEntityRepository studentsEntityRepository;
    @Autowired
    private CompanyEntityRepository companyEntityRepository;
    @Autowired
    private PlacedStudentsEntityRepository placedStudentsEntityRepository;
    @Autowired
    private ApplicationEntityRepository applicationEntityRepository;

    @Override
    public CompanyEntity newPlacement(CompanyCreationModel companyCreationModel) {

        if(companyEntityRepository.existsByCompanyName(companyCreationModel.getCompanyName()))
        {
            throw new IllegalArgumentException("Company Already exists");
        }

        CompanyEntity companyEntity= new CompanyEntity();
        BeanUtils.copyProperties(companyCreationModel,companyEntity);
        companyEntity.setStatus(true);

        companyEntityRepository.save(companyEntity);

        return companyEntity;
    }

    @Override
    public CompanyEntity deletePlacement(Long jobId) {
        if(!companyEntityRepository.existsByJobId(jobId)){
            throw new IllegalArgumentException("Job opportunity doesn't exists");
        }

        CompanyEntity companyEntity=companyEntityRepository.findByJobId(jobId);

        companyEntityRepository.delete(companyEntityRepository.findByJobId(jobId));

        return companyEntity;
    }

    @Override
    @Transactional
    public List<CompanyEntity> getAllPlacements() {

        return companyEntityRepository.findAll();
    }

    @Override
    public PlacedStudentsEntity addPlacedStudent(PlacedStudentsModel placedStudentsModel) {

        if(placedStudentsEntityRepository.existsByStudentIdAndJobId(placedStudentsModel.getStudentId(), placedStudentsModel.getJobId())){
            throw new IllegalArgumentException("Entry already exists");
        }

        if(!companyEntityRepository.existsByJobId(placedStudentsModel.getJobId())){
            throw new IllegalArgumentException("Job not found");
        }

        if(!studentsEntityRepository.existsByStudentId(placedStudentsModel.getStudentId())){
            throw new IllegalArgumentException("Student not found");
        }

        if(applicationEntityRepository.findByStudentIdAndJobId(placedStudentsModel.getStudentId(),placedStudentsModel.getJobId()).isPlacementStatus()==false){
            ApplicationEntity applicationEntity=applicationEntityRepository.findByStudentIdAndJobId(placedStudentsModel.getStudentId(), placedStudentsModel.getJobId());
            applicationEntity.setPlacementStatus(true);
            applicationEntityRepository.save(applicationEntity);
        }

        PlacedStudentsEntity placedStudentsEntity=new PlacedStudentsEntity();
        BeanUtils.copyProperties(placedStudentsModel,placedStudentsEntity);
        placedStudentsEntity.setStudentsEntity(studentsEntityRepository.findByStudentId(placedStudentsModel.getStudentId()));
        placedStudentsEntity.setCompanyEntity(companyEntityRepository.findByJobId(placedStudentsModel.getJobId()));

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

    @Override
    public ApplicationEntity applyForPlacements(ApplicationModel applicationModel) {

        if(applicationEntityRepository.existsByJobIdAndStudentId(applicationModel.getJobId(), applicationModel.getStudentId())){
            throw new IllegalArgumentException("Already applied for this job");
        }
        if(!companyEntityRepository.existsByJobId(applicationModel.getJobId())){
            throw new IllegalArgumentException("Job not found");
        }
        if(!studentsEntityRepository.existsByStudentId(applicationModel.getStudentId())){
            throw new IllegalArgumentException("Student not found");
        }
        if(studentsEntityRepository.findByStudentId(applicationModel.getStudentId()).getPercentage()<companyEntityRepository.findByJobId(applicationModel.getJobId()).getPercentage()){
            throw new IllegalArgumentException("Student percentage less than required");
        }

        ApplicationEntity applicationEntity=new ApplicationEntity();
        BeanUtils.copyProperties(applicationModel,applicationEntity);
        applicationEntity.setCompanyEntity(companyEntityRepository.findByJobId(applicationModel.getJobId()));
        applicationEntity.setStudentsEntity(studentsEntityRepository.findByStudentId(applicationModel.getStudentId()));

        applicationEntityRepository.save(applicationEntity);

        return applicationEntity;
    }

    @Override
    public CompanyEntity updatePlacement(CompanyUpdateModel companyUpdateModel){

        if(!companyEntityRepository.existsByJobId(companyUpdateModel.getJobId())){
            throw new IllegalArgumentException("No job found with the specified details");
        }

        CompanyEntity companyEntity=companyEntityRepository.findByJobId(companyUpdateModel.getJobId());

        if(companyUpdateModel.getAptitudeTest()!=null)
            companyEntity.setAptitudeTest(companyUpdateModel.getAptitudeTest());
        if(companyUpdateModel.getTechnicalTest()!=null)
            companyEntity.setTechnicalTest(companyUpdateModel.getTechnicalTest());
        if(companyUpdateModel.getCodingTest()!=null)
            companyEntity.setCodingTest(companyUpdateModel.getCodingTest());
        if(companyUpdateModel.getGroupDiscussion()!=null)
            companyEntity.setGroupDiscussion(companyUpdateModel.getGroupDiscussion());
        if(companyUpdateModel.getTechnicalInterview()!=null)
            companyEntity.setTechnicalTest(companyUpdateModel.getTechnicalInterview());
        if(companyUpdateModel.getHrInterview()!=null)
            companyEntity.setHrInterview(companyUpdateModel.getHrInterview());
        if(companyUpdateModel.getDirectorInterview()!=null)
            companyEntity.setDirectorInterview(companyUpdateModel.getDirectorInterview());
        if(companyUpdateModel.getStatus()!=null)
            companyEntity.setStatus(companyUpdateModel.getStatus());

        companyEntityRepository.save(companyEntity);

        return companyEntity;
    }

    @Override
    public List<ApplicationEntity> getAllApplications(long jobId) {

        if(!companyEntityRepository.existsByJobId(jobId)){
            throw new IllegalArgumentException("No company found");
        }

        return applicationEntityRepository.findAllByJobId(jobId);
    }

    @Override
    public List<ApplicationEntity> updateApplications(List<ApplicationModel> applicationModels) {
        if(!companyEntityRepository.existsByJobId(applicationModels.get(0).getJobId())){
            throw new IllegalArgumentException("No company found");
        }

        List<ApplicationEntity> applicationEntities=applicationEntityRepository.findAllByJobId(applicationModels.get(0).getJobId());

        for(int i=0; i<applicationModels.size();i++){

            if(applicationModels.get(i).getAptitudeTest()!=null)
                applicationEntities.get(i).setAptitudeTest(applicationModels.get(i).getAptitudeTest());
            if(applicationModels.get(i).getTechnicalTest()!=null)
                applicationEntities.get(i).setTechnicalTest(applicationModels.get(i).getTechnicalTest());
            if(applicationModels.get(i).getCodingTest()!=null)
                applicationEntities.get(i).setCodingTest(applicationModels.get(i).getCodingTest());
            if(applicationModels.get(i).getGroupDiscussion()!=null)
                applicationEntities.get(i).setGroupDiscussion(applicationModels.get(i).getGroupDiscussion());
            if(applicationModels.get(i).getTechnicalInterview()!=null)
                applicationEntities.get(i).setTechnicalInterview(applicationModels.get(i).getTechnicalInterview());
            if(applicationModels.get(i).getHrInterview()!=null)
                applicationEntities.get(i).setHrInterview(applicationModels.get(i).getHrInterview());
            if(applicationModels.get(i).getDirectorInterview()!=null)
                applicationEntities.get(i).setDirectorInterview(applicationModels.get(i).getDirectorInterview());
            if(applicationModels.get(i).getPlacementStatus()!=null)
                applicationEntities.get(i).setPlacementStatus(applicationModels.get(i).getPlacementStatus());

            applicationEntityRepository.save(applicationEntities.get(i));

            if(applicationModels.get(i).getPlacementStatus()==true){
                PlacedStudentsModel placedStudentsModel=PlacedStudentsModel.builder()
                        .studentId(applicationEntities.get(i).getStudentId())
                        .jobId(applicationEntities.get(i).getJobId())
                        .firstName(studentsEntityRepository.findByStudentId(applicationEntities.get(i).getStudentId()).getFirstName())
                        .lastName(studentsEntityRepository.findByStudentId(applicationEntities.get(i).getStudentId()).getLastName())
                        .build();
                addPlacedStudent(placedStudentsModel);
            }
        }

        return applicationEntities;
    }
}

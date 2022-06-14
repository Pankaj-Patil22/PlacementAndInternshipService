package com.oneacademy.placementandinternship.controller;

import com.oneacademy.placementandinternship.entity.ApplicationEntity;
import com.oneacademy.placementandinternship.entity.CompanyEntity;
import com.oneacademy.placementandinternship.entity.PlacedStudentsEntity;
import com.oneacademy.placementandinternship.model.ApplicationModel;
import com.oneacademy.placementandinternship.model.CompanyCreationModel;
import com.oneacademy.placementandinternship.model.CompanyUpdateModel;
import com.oneacademy.placementandinternship.service.PlacementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/placements")
public class PlacementCoordinatorController {

    @Autowired
    private PlacementService placementService;

    @PostMapping("/new-placement")
    public String newPlacement(@RequestBody CompanyCreationModel companyCreationModel)
    {
        try {
            placementService.newPlacement(companyCreationModel);
        }
        catch (Exception e)
        {
            return e.getLocalizedMessage();
        }

        return "Success";
    }

    @PatchMapping("/update-placement")
    public String updatePlacement(@RequestBody CompanyUpdateModel companyUpdateModel)
    {
        try{
            placementService.updatePlacement(companyUpdateModel);
        }
        catch (Exception e)
        {
            return e.getLocalizedMessage();
        }

        return "Success";
    }

    @DeleteMapping("/delete-placement/{jobId}")
    public String deletePlacement(@PathVariable("jobId") Long jobId)
    {
        try {
            placementService.deletePlacement(jobId);
        }
        catch (Exception e)
        {
            return e.getLocalizedMessage();
        }

        return "Success";
    }

    @GetMapping("/all-placements")
    public List<CompanyEntity> getAllPlacements()
    {
        return placementService.getAllPlacements();
    }

    @PostMapping("/apply-for-placement")
    public String applyForPlacement(@RequestBody ApplicationModel applicationModel)
    {
        try{
            placementService.applyForPlacements(applicationModel);
        }
        catch (Exception e)
        {
            return e.getLocalizedMessage();
        }

        return "Success";
    }


    @GetMapping("/applications/{jobId}")
    public List<ApplicationEntity> getAllApplications(@PathVariable("jobId") long jobId) {

        return placementService.getAllApplications(jobId);
    }

    @PatchMapping("/update-applications")
    public List<ApplicationEntity> updateApplications(@RequestBody List<ApplicationModel> applicationModels){
        return placementService.updateApplications(applicationModels);
    }


    @GetMapping("/all-placed-students")
    public List<PlacedStudentsEntity> getAllPlacedStudents()
    {
        return placementService.getAllPlacedStudents();
    }

}

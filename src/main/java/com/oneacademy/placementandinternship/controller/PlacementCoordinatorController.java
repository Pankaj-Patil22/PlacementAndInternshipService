package com.oneacademy.placementandinternship.controller;

import com.oneacademy.placementandinternship.entity.CompanyEntity;
import com.oneacademy.placementandinternship.model.CompanyModel;
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
    public String newPlacement(@RequestBody CompanyModel companyModel)
    {
        try {
            CompanyEntity companyEntity=placementService.newPlacement(companyModel);
        }
        catch (Exception e)
        {
            return e.getLocalizedMessage();
        }

        return "Success";
    }

    @DeleteMapping("/delete-placement/{jobId}")
    public String deletePlacement(@PathVariable("jobId") long jobId)
    {
        try {
            CompanyEntity companyEntity=placementService.deletePlacement(jobId);
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
}

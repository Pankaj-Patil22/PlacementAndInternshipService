package com.oneacademy.placementandinternship.controller;

import com.oneacademy.placementandinternship.entity.PlacedStudentsEntity;
import com.oneacademy.placementandinternship.model.PlacedStudentsModel;
import com.oneacademy.placementandinternship.service.PlacementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    PlacementService placementService;

    @PostMapping("/add-placed-student")
    public String addPlacedStudent(@RequestBody PlacedStudentsModel placedStudentsModel)
    {
        try{
            PlacedStudentsEntity placedStudentsEntity=placementService.addPlacedStudent(placedStudentsModel);
        }
        catch (Exception e)
        {
            return e.getLocalizedMessage();
        }

        return "Success";
    }

    @DeleteMapping("/delete-placed-student/{studentId}")
    public String deletePlacedStudent(@PathVariable("studentId") Long studentId)
    {
        try{
            PlacedStudentsEntity placedStudentsEntity=placementService.deletePlacedStudent(studentId);
        }
        catch (Exception e)
        {
            return e.getLocalizedMessage();
        }

        return "Success";
    }
}

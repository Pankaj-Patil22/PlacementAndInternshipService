package com.oneacademy.placementandinternship.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PlacedStudentsModel {

    @NotBlank( message= "Required Field")
    private String firstName;
    @NotBlank( message= "Required Field")
    private String lastName;
    @NotBlank( message= "Required Field")
    private String placedCompanyName;
}

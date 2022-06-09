package com.oneacademy.placementandinternship.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CompanyModel {

    @NotBlank( message= "Required Field")
    private String companyName;
    @NotBlank( message= "Required Field")
    private String jobProfile;
    @NotBlank( message= "Required Field")
    private String type;
    @NotBlank( message= "Required Field")
    private String jobDescription;
    @NotBlank( message= "Required Field")
    private String requirements;
}

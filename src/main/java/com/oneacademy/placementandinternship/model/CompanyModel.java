package com.oneacademy.placementandinternship.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CompanyModel {

    @NotNull(message = "Required Field")
    private Long jobId;
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
    @NotBlank(message = "Required Field")
    private double percentage;

    private Boolean aptitudeTest;
    private Boolean technicalTest;
    private Boolean codingTest;
    private Boolean groupDiscussion;
    private Boolean technicalInterview;
    private Boolean hrInterview;
    private Boolean directorInterview;
    private Boolean status;
}

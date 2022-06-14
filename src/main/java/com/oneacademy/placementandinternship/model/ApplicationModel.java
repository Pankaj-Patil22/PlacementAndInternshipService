package com.oneacademy.placementandinternship.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ApplicationModel {

    @NotBlank( message= "Required Field")
    private Long studentId;
    @NotBlank( message= "Required Field")
    private Long jobId;

    private Boolean aptitudeTest;
    private Boolean technicalTest;
    private Boolean codingTest;
    private Boolean groupDiscussion;
    private Boolean technicalInterview;
    private Boolean hrInterview;
    private Boolean directorInterview;
    private Boolean placementStatus;
}

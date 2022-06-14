package com.oneacademy.placementandinternship.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class PlacedStudentsModel {

    @NotBlank( message= "Required Field")
    private String firstName;
    @NotBlank( message= "Required Field")
    private String lastName;
    @NotBlank( message= "Required Field")
    private long studentId;
    @NotBlank(message = "Required Field")
    private long jobId;
}

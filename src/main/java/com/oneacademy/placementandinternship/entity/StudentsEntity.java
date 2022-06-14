package com.oneacademy.placementandinternship.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@Setter
public class StudentsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(insertable = false, nullable = false, unique = true, columnDefinition = "serial")// done this way because GeneratedValue only works with @Id
    private long studentId;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private double percentage;
    @OneToMany(mappedBy = "studentsEntity", orphanRemoval = true)
    @JsonIgnore
    private List<PlacedStudentsEntity> placedStudentsEntities = new ArrayList<>();

    @OneToMany(mappedBy = "studentsEntity", orphanRemoval = true,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ApplicationEntity> applicationEntities = new ArrayList<>();

}

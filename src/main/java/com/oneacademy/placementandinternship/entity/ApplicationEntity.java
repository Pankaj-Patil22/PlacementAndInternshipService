package com.oneacademy.placementandinternship.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@Setter
public class ApplicationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private long jobId;
    @Column(nullable = false)
    private long studentId;
    @ManyToOne(cascade = CascadeType.REMOVE, optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "company_entity_id", nullable = false)
    @JsonIgnore
    private CompanyEntity companyEntity;
    @ManyToOne(cascade = CascadeType.REMOVE, optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "students_entity_id", nullable = false)
    @JsonIgnore
    private StudentsEntity studentsEntity;

    private boolean aptitudeTest;
    private boolean technicalTest;
    private boolean codingTest;
    private boolean groupDiscussion;
    private boolean technicalInterview;
    private boolean hrInterview;
    private boolean directorInterview;
    private boolean placementStatus;//true if placed else false

}

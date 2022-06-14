package com.oneacademy.placementandinternship.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@Setter
@EqualsAndHashCode(exclude = { "applicationEntities"}) // This,
@ToString(exclude = { "applicationEntities"}) // and this

public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(insertable = false, nullable = false, unique = true, columnDefinition = "serial")// done this way because GeneratedValue only works with @Id
    private long jobId;
    @Column(nullable = false)
    private String companyName;
    @Column(nullable = false)
    private String jobProfile;
    @Column(nullable = false)
    private String type; //full time,internship
    @Column(nullable = false)
    private String jobDescription;
    @Column(nullable = false)
    private String requirements;
    @Column(nullable = false)
    private double percentage;

    private boolean aptitudeTest;
    private boolean technicalTest;
    private boolean codingTest;
    private boolean groupDiscussion;
    private boolean technicalInterview;
    private boolean hrInterview;
    private boolean directorInterview;
//    private Date creationTime;
//    private Date expirationTime;
    private boolean status;// if company is no longer recruiting then this is set to false
    @JsonIgnoreProperties("companyEntity")
    @OneToMany(mappedBy = "companyEntity", orphanRemoval = true,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ApplicationEntity> applicationEntities = new ArrayList<>();

    private final static AtomicLong jobIdCounter = new AtomicLong(System.nanoTime());// used for auto generating values

    @PrePersist
    void jobId() {
        this.jobId = jobIdCounter.incrementAndGet();
    }
}


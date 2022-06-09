package com.oneacademy.placementandinternship.entity;

import lombok.*;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.concurrent.atomic.AtomicLong;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@Setter
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

    private final static AtomicLong jobIdCounter = new AtomicLong(System.nanoTime());// used for auto generating values

    @PrePersist
    void jobId() {
        this.jobId = jobIdCounter.incrementAndGet();
    }
}

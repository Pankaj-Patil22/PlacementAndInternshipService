package com.oneacademy.placementandinternship.entity;


import lombok.*;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@Setter
public class PlacedStudentsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column( nullable = false)
    private long studentId;
    @Column(nullable = false)
    private long jobId;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;

//    @ManyToOne(cascade = CascadeType.REMOVE, optional = false)
//    @JoinColumn(name = "students_entity_id", nullable = false, unique = true)
//    private StudentsEntity studentsId;

    @ManyToOne(cascade = CascadeType.REMOVE, optional = false)
    @JoinColumn(name = "company_entity_id", nullable = false)
    private CompanyEntity companyEntity;

    @ManyToOne(cascade = CascadeType.REMOVE, optional = false)
    @JoinColumn(name = "students_entity_id", nullable = false)
    private StudentsEntity studentsEntity;

}

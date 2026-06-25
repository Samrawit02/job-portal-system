package com.samrit.job.model;

import com.samrit.job.domain.ExperienceLevel;
import com.samrit.job.domain.JobStatus;
import com.samrit.job.domain.JobType;
import com.samrit.job.domain.WorkMode;
import com.samrit.job.model.embeddable.JobLocation;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String requirements;
    @Column(nullable = false)
    private String benefits;
    @Column(nullable = false)
    private Long companyId;

//    private JobCategory category;
//    private Set<JobSkill> skills;
//    private Set<JobTag> tags;
    @Embedded
    private JobLocation location;
//    @Embedded
////    private SalaryRange salaryRange;
    @Enumerated(EnumType.STRING)
    private JobType jobType;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private WorkMode workMode;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ExperienceLevel experienceLevel;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private JobStatus status= JobStatus.DRAFT;

    private Integer openings=1;

    private LocalDate  applicationDeadline;

    private LocalDate expiresAt;
    private  Boolean active= true;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime updatedAt;

    private LocalDateTime publishedAt;
    private LocalDateTime closedAt;





}

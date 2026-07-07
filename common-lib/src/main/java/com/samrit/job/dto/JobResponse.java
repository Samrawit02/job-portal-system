package com.samrit.job.dto;

import com.samrit.job.domain.ExperienceLevel;
import com.samrit.job.domain.JobStatus;
import com.samrit.job.domain.JobType;
import com.samrit.job.domain.WorkMode;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobResponse {
    private Long id;
    private String title;
    private String description;
    private String requirements;
    private String responsibilities;
    private String benefits;
    private CompanyResponse company;
    private Long employerId;

    private JobCategoryResponse category;
    private Set<JobSkillResponse> skills;
    private Set<JobTagResponse> tags;


//    private JobLocation location;
    // location
    private String address;
    private String city;
    private String country;
    private String state;
    private String zipCode;

//    private SalaryRange salaryRange;
    // Salary
    private BigDecimal minSalary;
    private BigDecimal maxSalary;

    //Classification
    private JobType jobType;
    private WorkMode workMode;
    private ExperienceLevel experienceLevel;
    private JobStatus status;

    // Post details
    private Integer openings;
    private LocalDate applicationDeadline;
    private LocalDate expiresAt;
    private  Boolean active;

    // Timestamps
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime publishedAt;
    private LocalDateTime closedAt;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class JobCategoryResponse {

        private Long id;
        private String name;
        private  String slug;
        private String description;
        private  String iconUrl;
        private Boolean active;
        private Long parentId;
        private String parentName;
        private List<JobCategoryResponse> subCategories;
        private LocalDateTime createAt;
    }
}

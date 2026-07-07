package com.samrit.job.dto;

import com.samrit.job.domain.ExperienceLevel;
import com.samrit.job.domain.JobStatus;
import com.samrit.job.domain.JobType;
import com.samrit.job.domain.WorkMode;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobRequest {
    @NotNull(message = " Job title is required")
    private String title;

    @NotNull(message = " Job Description  is required")
    private String description;
    @NotNull(message = " Job requirement is required")
    private String requirement;
    private String responsibilities;
    private String benefits;
    @NotNull(message = " Job category is required")
    private Long categoryId;
    private Set<Long> skillIds;
    private Set<Long> tagIds;
    private String address;
    private String city;
    private String country;
    private String state;
    private String zipCode;
    @DecimalMin(value = "0.0" , message = "Min salary must not be negative")
    private BigDecimal minSalary;
    @DecimalMax(message = "Max salary must not be negative", value = "500000.00")
    private BigDecimal maxSalary;

    @NotNull(message = "Job type is required")
    private JobType jobType;
    @NotNull(message = "Work mode  is required")
    private WorkMode workMode;
    @NotNull(message = "Experience is required")
    private ExperienceLevel experienceLevel;
    private JobStatus status = JobStatus.DRAFT;
    @Min(value = 1, message = "Openings must be at least 1")
    private Integer openings;
    private LocalDate applicationDeadline;
    private LocalDate expiresAt;


}

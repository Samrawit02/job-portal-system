package com.samrit.job.payload;

import com.samrit.job.domain.ExperienceLevel;
import com.samrit.job.domain.JobStatus;
import com.samrit.job.domain.JobType;
import com.samrit.job.domain.WorkMode;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobSearchRequest {
    private String keyword;
    private Long categoryId;
    private List<Long>skillIds;
    private Long companyId;
    private String location;
    private BigDecimal minSalary;
    private BigDecimal maxSalary;
    private JobType jobType;
    private WorkMode workMode;
    private ExperienceLevel experienceLevel;
    private JobStatus status;
    private Integer minOpenings;
    private Integer maxOpenings;
}

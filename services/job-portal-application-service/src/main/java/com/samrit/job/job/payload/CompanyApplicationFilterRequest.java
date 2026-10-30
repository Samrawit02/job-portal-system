package com.samrit.job.job.payload;

import com.samrit.job.domain.AiShortListStatus;
import com.samrit.job.domain.ApplicationStatus;
import lombok.Data;

@Data
public class CompanyApplicationFilterRequest {
    private Long jobId;
    private ApplicationStatus status;
    private Boolean isStarred;
    private AiShortListStatus aiShortListStatus;
    private  Integer miniScore;
    private String sortBy;
}

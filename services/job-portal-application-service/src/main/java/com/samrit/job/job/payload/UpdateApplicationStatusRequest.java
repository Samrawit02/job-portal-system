package com.samrit.job.job.payload;

import com.samrit.job.domain.ApplicationStatus;
import lombok.Data;

@Data
public class UpdateApplicationStatusRequest {
    private ApplicationStatus status;
}

package com.samrit.job.job.service;

import com.samrit.job.domain.AiShortListStatus;
import com.samrit.job.dto.ApplicationResponse;
import com.samrit.job.job.model.Application;
import com.samrit.job.job.payload.CreateApplicationRequest;
import com.samrit.job.job.payload.WithdrawApplicationRequest;

import java.util.List;

public interface ApplicationService {
    ApplicationResponse createApplication(
            Long candidateId,
            CreateApplicationRequest request
    );
    ApplicationResponse  getApplicationById(Long id);
    List<ApplicationResponse> getMyApplication(Long candidateId);
    List<ApplicationResponse>getApplicationForCompany(Long companyId);
    List<ApplicationResponse>getApplicationForJob(Long jobId);
    ApplicationResponse updateStatus(Long applicationId,
                                     Long employerId,
                                     AiShortListStatus status);

    ApplicationResponse withdraw(Long applicationId, Long candidateId);

    ApplicationResponse withdraw(Long applicationId, Long candidateId,
                                 WithdrawApplicationRequest req);
    ApplicationResponse toggleStar(Long applicationId, Long employerId);
    void deleteApplication(Long applicationId, Long candidateId);

    Application getApplicationEntity(Long id);

}

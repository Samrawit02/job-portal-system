package com.samrit.job.job.service;

import com.samrit.job.domain.AiShortListStatus;
import com.samrit.job.domain.ApplicationStatus;
import com.samrit.job.dto.ApplicationResponse;
import com.samrit.job.job.model.Application;
import com.samrit.job.job.payload.CompanyApplicationFilterRequest;
import com.samrit.job.job.payload.CreateApplicationRequest;
import com.samrit.job.job.payload.UpdateApplicationStatusRequest;
import com.samrit.job.job.payload.WithdrawApplicationRequest;

import java.util.List;

public interface ApplicationService {
    ApplicationResponse createApplication(
            Long candidateId,
            CreateApplicationRequest request
    ) throws Exception;
    ApplicationResponse  getApplicationById(Long id) throws Exception;
    List<ApplicationResponse> getMyApplication(Long candidateId);
    List<ApplicationResponse>getApplicationForCompany(Long userId, CompanyApplicationFilterRequest filterRequest);
    List<ApplicationResponse>getApplicationForJob(Long jobId);
    ApplicationResponse updateStatus(Long applicationId,
                                     Long employerId,
                                     UpdateApplicationStatusRequest status) throws Exception;
    ApplicationResponse withdraw(Long applicationId, Long candidateId,
                                 WithdrawApplicationRequest req) throws Exception;
    ApplicationResponse toggleStar(Long applicationId, Long employerId) throws Exception;
    void deleteApplication(Long applicationId, Long candidateId) throws Exception;

    Application getApplicationEntity(Long id) throws Exception;

}

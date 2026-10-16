package com.samrit.job.job.service.imp;

import com.samrit.job.domain.AiShortListStatus;
import com.samrit.job.dto.ApplicationResponse;
import com.samrit.job.job.model.Application;
import com.samrit.job.job.payload.CreateApplicationRequest;
import com.samrit.job.job.payload.WithdrawApplicationRequest;
import com.samrit.job.job.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {
    @Override
    public ApplicationResponse createApplication(Long candidateId, CreateApplicationRequest request) {
        return null;
    }

    @Override
    public ApplicationResponse getApplicationById(Long id) {
        return null;
    }

    @Override
    public List<ApplicationResponse> getMyApplication(Long candidateId) {
        return null;
    }

    @Override
    public List<ApplicationResponse> getApplicationForCompany(Long companyId) {
        return null;
    }

    @Override
    public List<ApplicationResponse> getApplicationForJob(Long jobId) {
        return null;
    }

    @Override
    public ApplicationResponse updateStatus(Long applicationId, Long employerId, AiShortListStatus status) {
        return null;
    }

    @Override
    public ApplicationResponse withdraw(Long applicationId, Long candidateId) {
        return null;
    }

    @Override
    public ApplicationResponse withdraw(Long applicationId, Long candidateId, WithdrawApplicationRequest req) {
        return null;
    }

    @Override
    public ApplicationResponse toggleStar(Long applicationId, Long employerId) {
        return null;
    }

    @Override
    public void deleteApplication(Long applicationId, Long candidateId) {

    }

    @Override
    public Application getApplicationEntity(Long id) {
        return null;
    }
}

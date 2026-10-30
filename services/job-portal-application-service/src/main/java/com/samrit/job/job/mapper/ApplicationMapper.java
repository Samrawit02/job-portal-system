package com.samrit.job.job.mapper;

import com.samrit.job.dto.ApplicationResponse;
import com.samrit.job.dto.CompanyResponse;
import com.samrit.job.dto.JobResponse;
import com.samrit.job.job.model.Application;
import com.samrit.job.job.payload.CreateApplicationRequest;
import com.samrit.job.response.UserResponse;

public class ApplicationMapper {

    public  static Application toEntity(CreateApplicationRequest request,
                                        Long candidateId,
                                        Long companyId,
                                        Long employerId){
        if(request==null) return  null;

        return  Application.builder()
                .candidateId(candidateId)
                .jobId(request.getJobId())
                .companyId(companyId)
                .employerId(request.getEmployerId())
                .resumeId(request.getResumeId())
                .coverLetter(request.getCoverLetter())
                .expectedSalary(request.getExpectedSalary())
                .availableFrom(request.getAvailableFrom())
                .build();
    }

    public static ApplicationResponse toResponse(Application application,
                                                 JobResponse jobResponse,
                                                 CompanyResponse companyResponse,
                                                 UserResponse candidate
//                                                 ApplicationScreening screening
                                                 ){
        return  ApplicationResponse.builder()
                .id(application.getId())
                .candidateId(candidate)
                .employerId(application.getEmployerId())
                .job(jobResponse)
                .company(companyResponse)
                .applicationStatus(application.getApplicationStatus())
                .coverLetter(application.getCoverLetter())
                .resumeId(application.getResumeId())
                .expectedSalary(application.getExpectedSalary())
                .availableFrom(application.getAvailableFrom())
                .isStarred(application.getIsStarred())
                .withdrawnAt(application.getWithdrawnAt())
                .withdrawnReason(application.getWithdrawnReason())
                .appliedAt(application.getAppliedAt())
                .updatedAt(application.getUpdatedAt())
                .build();

    }
}

package com.samrit.job.service;

import com.samrit.job.dto.WorkExperienceResponse;
import com.samrit.job.model.WorkExperience;
import com.samrit.job.payload.WorkExperienceRequest;

import java.util.List;

public interface WorkExperienceService {
    WorkExperienceResponse addWorkExperience(Long resumeId, Long candidateId, WorkExperienceRequest request) throws Exception;
    List<WorkExperienceResponse> getWorkExperiences(Long resumeId);
    WorkExperienceResponse updateWorkExperience(
            Long resumeId,Long candidateId, Long workExperienceId, WorkExperienceRequest request
    ) throws Exception;
    void deleteWorkExperience(Long resumeId, Long workExperienceId, Long candidateId ) throws Exception;
    WorkExperience getWorkExperienceEntityById(Long workExperienceId) throws Exception;
}

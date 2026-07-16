package com.samrit.job.service;

import com.samrit.job.dto.EducationResponse;
import com.samrit.job.payload.EducationRequest;

import java.util.List;

public interface EducationService {
    EducationResponse addEducation(Long resumeId, Long candidateId, EducationRequest request) throws Exception;
    List<EducationResponse> getEducations(Long resumeId);
    EducationResponse updateEducationId(Long educationId, Long resumeId, Long candidateId, EducationRequest request) throws Exception;

    void deleteEducation(Long educationId, Long resumeId, Long candidateId) throws Exception;
}

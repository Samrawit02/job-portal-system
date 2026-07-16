package com.samrit.job.service;


import com.samrit.job.dto.PersonalInfoResponse;
import com.samrit.job.dto.ResumeResponse;
import com.samrit.job.model.Resume;
import com.samrit.job.payload.ResumeRequest;

import java.util.List;

public interface ResumeService {

    ResumeResponse createResume (Long candidateId , ResumeRequest request);
    ResumeResponse getResumesById(Long resumeId, Long candidateId) throws Exception;
    List<ResumeResponse> getMyResumes(Long candidateId);
    ResumeResponse updatePersonalInformation (Long resumeId, Long candidateId, PersonalInfoResponse personalInfo) throws Exception;
    ResumeResponse updateSummary(Long resumeId, Long candidateId, String summary) throws Exception;
    ResumeResponse setDefaultResume(Long resumeId, Long candidateId) throws Exception;
    void deleteResume(Long resumeId, Long candidateId) throws Exception;
    Resume getResumeEntityById(Long resumeId) throws Exception;


}

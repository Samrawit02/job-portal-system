package com.samrit.job.service;

import com.samrit.job.dto.ProjectResponse;
import com.samrit.job.payload.ProjectRequest;

import java.util.List;

public interface ProjectService {

    ProjectResponse addProject (Long resumeId, Long candidateId, ProjectRequest request) throws Exception;
    List<ProjectResponse> getAllProjects(Long resumeId);
    ProjectResponse updateProject(Long projectId, Long resumeId, Long candidateId, ProjectRequest request) throws Exception;
    void deleteProject(Long projectId, Long resumeId, Long candidateId) throws Exception;

}

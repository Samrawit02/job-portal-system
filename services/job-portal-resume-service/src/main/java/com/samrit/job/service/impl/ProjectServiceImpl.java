package com.samrit.job.service.impl;

import com.samrit.job.Mapper.ResumeMapper;
import com.samrit.job.dto.ProjectResponse;
import com.samrit.job.model.Project;
import com.samrit.job.model.Resume;
import com.samrit.job.payload.ProjectRequest;
import com.samrit.job.repo.ProjectRepo;
import com.samrit.job.service.ProjectService;
import com.samrit.job.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.w3c.dom.events.EventException;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepo projectRepo;
    private  final ResumeService resumeService;

    @Override
    public ProjectResponse addProject(Long resumeId, Long candidateId, ProjectRequest request) throws Exception {
        Resume resume = resumeService.getResumeEntityById(resumeId);
        asserOwner(resume, candidateId);

        Project project = Project.builder()
                .resume(resume)
                .title(resume.getTitle())
                .description(request.getDescription())
                .technologies(request.getTechnologies())
                .projectUrl(request.getProjectUrl())
                .sourceCodeUrl(request.getSourceCodeUrl())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .isOngoing(Boolean.TRUE.equals(request.getIsOngoing()))
                .displayOrder(request.getDisplayOrder() !=null ? request.getDisplayOrder() : 0)

                .build();
        return ResumeMapper.toProjectResponse(projectRepo.save(project));
    }

    @Override
    public List<ProjectResponse> getAllProjects(Long resumeId) {
        return projectRepo.findByResume_IdAndOrderByDisplayOrderAsc(resumeId)
                .stream()
                .map(ResumeMapper::toProjectResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProjectResponse updateProject(Long projectId, Long resumeId, Long candidateId, ProjectRequest request) throws Exception {
        Project project = projectRepo.findById(projectId)
                        .orElseThrow(()->
                                new Exception("Project not found"));
        asserOwner(project.getResume(), candidateId);

        project.setTitle(request.getTitle());
        project.setDescription(request.getDescription());
        project.setTechnologies(request.getTechnologies());
        project.setProjectUrl(request.getProjectUrl());
        project.setSourceCodeUrl(request.getSourceCodeUrl());
        project.setStartDate(request.getStartDate());
        project.setEndDate(request.getEndDate());
        project.setIsOngoing(request.getIsOngoing());
        project.setIsOngoing(Boolean.TRUE.equals(request.getIsOngoing()));
        if(request.getDisplayOrder() !=null) project.setDisplayOrder(request.getDisplayOrder());
    return ResumeMapper.toProjectResponse(projectRepo.save(project));
    }

    @Override
    public void deleteProject(Long projectId, Long resumeId, Long candidateId) throws Exception {
        Project project = projectRepo.findById(projectId)
                .orElseThrow(()->
                        new Exception("Project not found"));
        asserOwner(project.getResume(), candidateId);
        projectRepo.delete(project);
    }
    private void asserOwner(Resume resume, Long candidateId) throws Exception {
        if(!resume.getCandidateId().equals(candidateId)){
            throw new Exception("resume not found with the given id");
        }
    }
}

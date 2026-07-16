package com.samrit.job.service.impl;

import com.samrit.job.Mapper.WorkExperienceMapper;
import com.samrit.job.dto.WorkExperienceResponse;
import com.samrit.job.model.Resume;
import com.samrit.job.model.WorkExperience;
import com.samrit.job.payload.WorkExperienceRequest;
import com.samrit.job.repo.WorkExperienceRepo;
import com.samrit.job.service.ResumeService;
import com.samrit.job.service.WorkExperienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.w3c.dom.events.EventException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkExperienceServiceImpl implements WorkExperienceService {

    private final ResumeService resumeService;
    private final WorkExperienceRepo workExperienceRepo;

    @Override
    public WorkExperienceResponse addWorkExperience(Long resumeId, Long candidateId, WorkExperienceRequest request) throws Exception {
        Resume resume = resumeService.getResumeEntityById(resumeId);

        assertOwner(resume, candidateId);

        WorkExperience workExperience =
                WorkExperience.builder()
                        .resume(resume)
                        .companyName(request.getCompanyName())
                        .companyLogoUrl(request.getCompanyLogoUrl())
                        .jobTitle(request.getJobTitle())
                        .employmentType(request.getEmploymentType())
                        .location(request.getLocation())
                        .startDate(request.getStartDate())
                        .endDate(request.getEndDate())
                        .isCurrentJob(request.getIsCurrentJob())
                        .description(request.getDescription())
                        .technologies(request.getTechnologies() !=null ? request.getTechnologies() : List.of())
                        .displayOrder(request.getDisplayOrder() !=null ? request.getDisplayOrder(): 0)

                        .build();
        return WorkExperienceMapper.toWorkExperienceResponse(workExperienceRepo.save(workExperience));
    }
    @Override
    public List<WorkExperienceResponse> getWorkExperiences(Long resumeId) {
        return workExperienceRepo.findByResume_IdOrderByDisplayOrderAsc(resumeId)
                .stream()
                .map(WorkExperienceMapper::toWorkExperienceResponse)
                .collect(Collectors.toList());
    }
    @Override
    public WorkExperienceResponse updateWorkExperience(Long resumeId, Long candidateId, Long workExperienceId, WorkExperienceRequest request) throws Exception {
       WorkExperience  workExperience = getWorkExperienceEntityById(resumeId);
       assertOwner(workExperience.getResume(), candidateId);

       workExperience.setCompanyName(request.getCompanyName());
       workExperience.setCompanyLogoUrl(request.getCompanyLogoUrl());
       workExperience.setJobTitle(request.getJobTitle());
       workExperience.setEmploymentType(request.getEmploymentType());
       workExperience.setLocation(request.getLocation());
       workExperience.setStartDate(request.getStartDate());
       workExperience.setEndDate(request.getEndDate());
       workExperience.setIsCurrentJob(Boolean.TRUE.equals(request.getIsCurrentJob()));
       workExperience.setDescription(request.getDescription());
       if(request.getTechnologies()!=null) workExperience.setTechnologies(request.getTechnologies());
       if(request.getDescription() !=null) workExperience.setDisplayOrder(request.getDisplayOrder());

       return WorkExperienceMapper.toWorkExperienceResponse(workExperienceRepo.save(workExperience));

    }

    @Override
    public void deleteWorkExperience(Long resumeId, Long workExperienceId, Long candidateId) throws Exception {
        WorkExperience  workExperience = getWorkExperienceEntityById(resumeId);
        assertOwner(workExperience.getResume(), candidateId);
        workExperienceRepo.delete(workExperience);
    }

    @Override
    public WorkExperience getWorkExperienceEntityById(Long workExperienceId) throws Exception {

        return workExperienceRepo.findById(workExperienceId).orElseThrow(
                ()-> new Exception("Work Experience not found")
        );
    }

    private void assertOwner(Resume resume, Long candidateId
    ) throws Exception {
        if(!resume.getCandidateId().equals(candidateId)){
            throw new Exception("resume not found");
        }
    }
}

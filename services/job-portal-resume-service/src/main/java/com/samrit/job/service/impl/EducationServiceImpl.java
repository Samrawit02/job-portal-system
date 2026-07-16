package com.samrit.job.service.impl;

import com.samrit.job.Mapper.ResumeMapper;
import com.samrit.job.dto.EducationResponse;
import com.samrit.job.model.Education;
import com.samrit.job.model.Resume;
import com.samrit.job.payload.EducationRequest;
import com.samrit.job.repo.EducationRepo;
import com.samrit.job.service.EducationService;
import com.samrit.job.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EducationServiceImpl implements EducationService {

    private final ResumeService resumeService;
    private final EducationRepo educationRepo;
    @Override
    public EducationResponse addEducation(Long resumeId, Long candidateId, EducationRequest request) throws Exception {
        Resume resume = resumeService.getResumeEntityById(resumeId);
        asserOwner(resume, candidateId);
        Education education = Education.builder()
                .resume(resume)
                .institutionName(request.getInstitutionName())
                .degree(request.getDegree())
                .grade(request.getGrade())
                .fieldOfStudy(request.getFieldOfStudy())
                .grade(request.getGrade())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .inCurrentlyStudying(request.getInCurrentlyStudying())
                .description(request.getDescription())
                .displayOrder(request.getDisplayOrder() !=null ? request.getDisplayOrder() : 0)
                .build();

        return ResumeMapper.toEducationResponse(educationRepo.save(education));
    }

    @Override
    public List<EducationResponse> getEducations(Long resumeId) {
        return educationRepo.findByResume_IdAndOrderByDisplayOrderAsc(resumeId)
                .stream()
                .map(ResumeMapper::toEducationResponse)
                .collect(Collectors.toList());
    }

    @Override
    public EducationResponse updateEducationId(Long educationId, Long resumeId, Long candidateId, EducationRequest request) throws Exception {
        Education education = educationRepo.findById(educationId)
                .orElseThrow(()->
                        new Exception("Education not found")
                );

        asserOwner(education.getResume(), candidateId);

        education.setInstitutionName(request.getInstitutionName());
        education.setDegree(request.getDegree());
        education.setFieldOfStudy(request.getFieldOfStudy());
        education.setGrade(request.getGrade());
        education.setStartDate(request.getStartDate());
        education.setEndDate(request.getEndDate());
        education.setInCurrentlyStudying(request.getInCurrentlyStudying());
        education.setDescription(request.getDescription());
        if (request.getDisplayOrder() !=null) education.setDisplayOrder(request.getDisplayOrder());

        return ResumeMapper.toEducationResponse(educationRepo.save(education));
    }

    @Override
    public void deleteEducation(Long educationId, Long resumeId, Long candidateId) throws Exception {
        Education education = educationRepo.findById(educationId)
                .orElseThrow(()->
                        new Exception("Education not found")
                );

        asserOwner(education.getResume(), candidateId);
        educationRepo.delete(education);
    }
    private void asserOwner(Resume resume, Long candidateId) throws Exception {
        if(!resume.getCandidateId().equals(candidateId)){
            throw new Exception("resume not found with the given id");
        }
    }
}

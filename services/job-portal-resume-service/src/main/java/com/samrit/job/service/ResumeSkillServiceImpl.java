package com.samrit.job.service;

import com.samrit.job.Mapper.ResumeMapper;
import com.samrit.job.dto.ResumeSkillResponse;
import com.samrit.job.model.Resume;
import com.samrit.job.model.ResumeSkill;
import com.samrit.job.payload.ResumeSkillRequest;
import com.samrit.job.repo.ResumeSkillRepo;
import com.samrit.job.service.impl.ResumeSkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ResumeSkillServiceImpl implements ResumeSkillService {

    private final ResumeSkillRepo resumeSkillRepo;
    private final ResumeService resumeService;
    @Override
    public ResumeSkillResponse addSkill(Long resumeId, Long candidateId, ResumeSkillRequest request) throws Exception {
        Resume resume = resumeService.getResumeEntityById(resumeId);
        asserOwner(resume, candidateId);

        ResumeSkill skill = ResumeSkill.builder()
                .resume(resume)
                .skillName(request.getSkillName())
                .proficiencyLevel(request.getProficiencyLevel())
                .yearsOfExperience(request.getYearsOfExperience())
                .displayOrder(request.getDisplayOrder() !=null ? request.getDisplayOrder() : 0)
                .build();

        return ResumeMapper.toSkillResponse(resumeSkillRepo.save(skill));
    }

    @Override
    public List<ResumeSkillResponse> getSkills(Long resumeId) {
        return resumeSkillRepo.findByResume_IdOrderByDisplayOrderAsc(resumeId)
                .stream()
                .map(ResumeMapper::toSkillResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ResumeSkillResponse updateSkill(Long skillId, Long resumeId, Long candidateId, ResumeSkillRequest request) throws Exception {
        Resume resume = resumeService.getResumeEntityById(resumeId);
        ResumeSkill skill = resumeSkillRepo.findById(skillId)
                        .orElseThrow(()->
                            new Exception("Skill not found")
                        );
        asserOwner(resume, candidateId);

        skill.setSkillName(request.getSkillName());
        skill.setProficiencyLevel(request.getProficiencyLevel());
        skill.setYearsOfExperience(request.getYearsOfExperience());

        return ResumeMapper.toSkillResponse(resumeSkillRepo.save(skill));

    }

    @Override
    public void deleteSkill(Long skillId, Long resumeId, Long candidateId) throws Exception {
        Resume resume = resumeService.getResumeEntityById(resumeId);
        ResumeSkill skill = resumeSkillRepo.findById(skillId)
                .orElseThrow(()->
                        new Exception("Skill not found")
                );
        asserOwner(resume, candidateId);
        resumeSkillRepo.delete(skill);

    }
    private void asserOwner(Resume resume, Long candidateId) throws Exception {
        if(!resume.getCandidateId().equals(candidateId)){
            throw new Exception("resume not found with the given id");
        }
    }
}

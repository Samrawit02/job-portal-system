package com.samrit.job.service.impl;

import com.samrit.job.dto.ResumeSkillResponse;
import com.samrit.job.model.ResumeSkill;
import com.samrit.job.payload.ResumeSkillRequest;

import java.util.List;

public interface ResumeSkillService {

    ResumeSkillResponse addSkill (Long resumeId, Long candidateId, ResumeSkillRequest request) throws Exception;
    List<ResumeSkillResponse>getSkills(Long resumeId);
    ResumeSkillResponse updateSkill(
            Long skillId, Long resumeId, Long candidateId,
            ResumeSkillRequest request
    ) throws Exception;

    void deleteSkill(Long skillId, Long resumeId, Long candidateId) throws Exception;

//    ResumeSkill getSkillEntity(ski)

}

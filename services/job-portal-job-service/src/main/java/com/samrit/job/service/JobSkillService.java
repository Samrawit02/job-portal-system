package com.samrit.job.service;

import com.samrit.job.dto.JobSkillResponse;
import com.samrit.job.model.JobSkill;
import com.samrit.job.payload.JobSkillRequest;

import java.util.List;
import java.util.Set;

public interface JobSkillService {

    JobSkillResponse createSkill(JobSkillRequest request) throws Exception;
    List<JobSkillResponse> getAllSkills();
    JobSkillResponse getSkillById(Long id) throws Exception;
    JobSkillResponse updateSkill(Long id, JobSkillRequest request) throws Exception;
    void deleteSkill(Long id) throws Exception;
    Set<JobSkill> getSkillsByIds(Set<Long> ids);

}

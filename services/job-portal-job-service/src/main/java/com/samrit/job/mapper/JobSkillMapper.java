package com.samrit.job.mapper;

import com.samrit.job.dto.JobSkillResponse;
import com.samrit.job.model.JobSkill;

public class JobSkillMapper {

    public  static JobSkillResponse toJobSkillResponse(JobSkill skill){
        return JobSkillResponse.builder()
                .id(skill.getId())
                .name(skill.getName())
                .slug(skill.getSlug())
                .skillCategory(skill.getCategory())
                .active(skill.getActive())
                .build();
    }
}

package com.samrit.job.mapper;

import com.samrit.job.dto.JobSkillResponse;
import com.samrit.job.dto.JobTagResponse;
import com.samrit.job.model.JobTag;

public class JobTagMapper {

    public static JobTagResponse toTagResponse(JobTag jobTag){
      return   JobTagResponse.builder()
                .id(jobTag.getId())
                .name(jobTag.getName())
                .slug(jobTag.getSlug())
                .build();

    }
}

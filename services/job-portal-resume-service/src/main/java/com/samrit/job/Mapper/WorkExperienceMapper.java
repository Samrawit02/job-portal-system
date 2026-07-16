package com.samrit.job.Mapper;

import com.samrit.job.dto.WorkExperienceResponse;
import com.samrit.job.model.WorkExperience;

public class WorkExperienceMapper {

    public  static WorkExperienceResponse toWorkExperienceResponse(WorkExperience workExperience){
        if(workExperience ==null) return  null;

        return WorkExperienceResponse.builder()
                .id(workExperience.getId())
                .companyName(workExperience.getCompanyName())
                .companyLogoUrl(workExperience.getCompanyLogoUrl())
                .jobTitle(workExperience.getJobTitle())
                .employmentType(workExperience.getEmploymentType())
                .location(workExperience.getLocation())
                .startDate(workExperience.getStartDate())
                .endDate(workExperience.getEndDate())
                .isCurrentJob(workExperience.getIsCurrentJob())
                .description(workExperience.getDescription())
                .technologies(workExperience.getTechnologies())
                .displayOrder(workExperience.getDisplayOrder())
                .build();
    }
}

package com.samrit.job.dto;

import com.samrit.job.domain.ResumeTemplate;
import com.samrit.job.domain.ResumeVisibility;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResumeResponse {

    private Long id;
    private Long candidateId;
    private String title;
    private ResumeTemplate template ;
    private ResumeVisibility visibility;
    private Boolean isDefault;
    private String summary;
    private PersonalInfoResponse personalInfo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer completionScore;

    // todo:
    // private List<WorkExperienceResponse) workExperiences;


}

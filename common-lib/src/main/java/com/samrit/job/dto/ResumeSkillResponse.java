package com.samrit.job.dto;


import com.samrit.job.domain.ProficiencyLevel;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResumeSkillResponse {

    private Long id;
    private String skillName;
    private ProficiencyLevel proficiencyLevel;
    private Integer yearsOfExperience;
    private Integer displayOrder;
    private LocalDateTime createdAt;
}

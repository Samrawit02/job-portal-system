package com.samrit.job.payload;


import com.samrit.job.domain.ProficiencyLevel;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import javax.swing.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResumeSkillRequest {
    @NotBlank(message = "Skill name is required")
    @Size(max=100, message = "Skill name must not excedd 100 characters")
    private String skillName;

    @NotNull(message = "Proficiency leve is required")
    private ProficiencyLevel proficiencyLevel;

    @Min(value = 0, message = "Years of experience must not be negative")
    private Integer yearsOfExperience;
    private Integer displayOrder ;

}

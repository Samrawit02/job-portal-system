package com.samrit.job.dto;

import com.samrit.job.domain.SkillCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobSkillResponse {

    private Long id;
    private String name;
    private String slug;
    private SkillCategory skillCategory;
    private Boolean active;
}

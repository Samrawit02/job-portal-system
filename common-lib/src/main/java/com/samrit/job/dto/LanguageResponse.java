package com.samrit.job.dto;

import com.samrit.job.domain.LanguageProficiency;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class LanguageResponse {
    private Long id;
    private String languageName;
    private LanguageProficiency languageProficiency;
    private Integer displayOrder=0;
}

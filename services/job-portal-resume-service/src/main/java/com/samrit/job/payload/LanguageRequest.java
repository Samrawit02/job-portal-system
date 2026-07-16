package com.samrit.job.payload;

import com.samrit.job.domain.LanguageProficiency;
import jakarta.persistence.Column;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class LanguageRequest {
    private String languageName;
    private LanguageProficiency languageProficiency;
    private Integer displayOrder;
}

package com.samrit.job.payload;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class EducationRequest {
    @NotBlank(message = "Institution name is required")
    private String institutionName;
    @NotBlank(message = "Degree is required")
    private String degree;
    private String fieldOfStudy;
    private String grade;
    @NotBlank(message = "Start Date  is required")
    private LocalDate startDate;
    private LocalDate endDate;
    @Builder.Default
    private Boolean inCurrentlyStudying=false;
    private String description;
    private Integer displayOrder;
}

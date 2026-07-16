package com.samrit.job.dto;

import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class EducationResponse {
    private Long id;
    private String institutionName;
    private String degree;
    private String fieldOfStudy;
    private String grade;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean inCurrentlyStudying;
    private String description;
    private Integer displayOrder;
}

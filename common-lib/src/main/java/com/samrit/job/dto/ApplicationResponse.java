package com.samrit.job.dto;

import com.samrit.job.domain.AiShortListStatus;
import com.samrit.job.domain.ApplicationStatus;
import com.samrit.job.response.UserResponse;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationResponse {
    private Long id;
    private UserResponse candidateId;
    private JobResponse jobId;
    private CompanyResponse companyId;
    private Long EmployerId;
    private Long resumeId;
    private ApplicationStatus applicationStatus;
    private String coverLetter;
    private BigDecimal expectedSalary;
    private LocalDate availableFrom;
    private Boolean isStarred;
// Todo
//    private List<ApplicationNoteResponse> notes;
    private LocalDateTime withdrawnAt;
    private String withdrawnReason;
    private LocalDateTime appliedAt;

    private LocalDateTime updatedAt;

    // private ApplicationScreeningResponse screening;
}

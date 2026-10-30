package com.samrit.job.dto;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ApplicationNoteResponse {
    private Long id;
    private Long addedByUserId;
    private String content;
    private LocalDateTime createdAt;
}

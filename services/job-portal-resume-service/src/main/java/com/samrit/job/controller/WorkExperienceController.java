package com.samrit.job.controller;

import com.samrit.job.dto.ApiMessage;
import com.samrit.job.dto.WorkExperienceResponse;
import com.samrit.job.payload.WorkExperienceRequest;
import com.samrit.job.service.WorkExperienceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resumes/{resumeId}/work-experiences")
@RequiredArgsConstructor
public class WorkExperienceController {

    private final WorkExperienceService workExperienceService;

    @PostMapping
    public ResponseEntity<WorkExperienceResponse> addWorkExperience(
            @PathVariable Long resumeId,
            @RequestHeader("X-User-Id") Long candidateId,
            @RequestBody @Valid WorkExperienceRequest req

    ) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(workExperienceService.addWorkExperience(resumeId,candidateId,req));

    }

    @GetMapping
    public ResponseEntity<List<WorkExperienceResponse>> getWorkExperiences(
            @PathVariable Long resumeId

    ){
        return ResponseEntity.ok(workExperienceService.getWorkExperiences(resumeId));
    }

    @PutMapping("/{experienceId")
    public ResponseEntity<WorkExperienceResponse> updateWorkExperience(
            @PathVariable Long resumeId,
            @PathVariable Long workExperienceId,
            @RequestHeader("X-User-Id") Long candidateId,
            @RequestBody @Valid WorkExperienceRequest request
    ) throws Exception {
        return ResponseEntity.ok(workExperienceService.updateWorkExperience(resumeId,candidateId, workExperienceId, request));

    }
    @DeleteMapping("/{experienceId}")
    public ResponseEntity<ApiMessage> deleteWorkExperience(
            @PathVariable Long resumeId,
            @PathVariable Long experienceId,
            @RequestHeader ("X-User-Id") Long candidateId
    ) throws Exception {
        workExperienceService.deleteWorkExperience(resumeId, experienceId, candidateId);
        return ResponseEntity.ok(new ApiMessage("Work Experience deleted Successfully", true));
    }
}

package com.samrit.job.controller;


import com.samrit.job.dto.ApiMessage;
import com.samrit.job.dto.EducationResponse;
import com.samrit.job.payload.EducationRequest;
import com.samrit.job.service.EducationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/resume/{resumeId}/education")
public class EducationController {
    private final EducationService educationService;

    @PostMapping
    public ResponseEntity<EducationResponse> addEducation(
            @PathVariable Long resumeId,
            @RequestHeader("X-User-Id") Long candidateId,
            @RequestBody @Valid EducationRequest request
            ) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(educationService.addEducation(resumeId, candidateId,request));
    }

    @GetMapping
    public ResponseEntity<List<EducationResponse>> getEducations(
            @PathVariable Long resumeId
    ){
        return ResponseEntity.ok(educationService.getEducations(resumeId));
    }
    @PutMapping("/{educationId}")
    public ResponseEntity<EducationResponse> updateEducation(
            @PathVariable Long resumeId,
            @PathVariable Long educationId,
            @RequestHeader ("X-User-Id") Long candidateId,
            @RequestBody @Valid EducationRequest request
    ) throws Exception {
        return  ResponseEntity.ok(educationService.updateEducationId(educationId, resumeId, candidateId, request));
    }
    @DeleteMapping("/{educationId}")
    public ResponseEntity<ApiMessage> deleteEducation(
            @PathVariable Long resumeId,
            @PathVariable Long educationId,
            @RequestHeader("X-User-Id") Long candidateId
    ) throws Exception {
        educationService.deleteEducation(educationId, resumeId,candidateId);
        return  ResponseEntity.ok(new ApiMessage("Education successfully deleted", true));
    }
}

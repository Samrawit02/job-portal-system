package com.samrit.job.controller;


import com.samrit.job.dto.ApiMessage;
import com.samrit.job.dto.PersonalInfoResponse;
import com.samrit.job.dto.ResumeResponse;
import com.samrit.job.payload.ResumeRequest;
import com.samrit.job.service.ResumeService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resumes")
@RequiredArgsConstructor
public class ResumeController {

    private ResumeService resumeService;

    @PostMapping
    public ResponseEntity<ResumeResponse>createResume(
            @RequestHeader ("X-User-Id") Long candidateId,
            @RequestBody @Valid ResumeRequest request
            ){
        return  ResponseEntity.status(HttpStatus.CREATED)
                .body(resumeService.createResume(candidateId,request));
    }
    @GetMapping("/{resumeId}")
    public ResponseEntity<ResumeResponse> getResumeById(
            @PathVariable Long resumeId,
            @RequestHeader("X-User-Id") Long candidateId
    ) throws Exception {
        return ResponseEntity.ok(resumeService.getResumesById(resumeId,candidateId));
    }
    @GetMapping("my-resume")
    public ResponseEntity<List<ResumeResponse>> getMyResume(
            @RequestHeader("X-User-Id") Long candidateId
    ){
        return ResponseEntity.ok(resumeService.getMyResumes(candidateId));
    }

    @PutMapping("/{resumeId}/personal-info")
    public ResponseEntity<ResumeResponse> updatePersonalInfo(
            @PathVariable Long resumeId,
            @RequestHeader("X-User-Id") Long candidateId,
            @RequestBody @Valid PersonalInfoResponse personalInfo
            ) throws Exception {
        return ResponseEntity.ok(resumeService.updatePersonalInformation(resumeId,candidateId, personalInfo));
    }
    @PatchMapping("/{resumeId}/summary")
    public ResponseEntity<ResumeResponse> updateSummary(
            @PathVariable Long resumeId,
            @RequestHeader("X-User-Id") Long candidateId,
            @RequestParam String summary
    ) throws Exception {
        return  ResponseEntity.ok(resumeService.updateSummary(resumeId, candidateId, summary));
    }

    @PatchMapping("/{resumeId}/set-default")
    public ResponseEntity<ResumeResponse> setDefaultResume(
            @PathVariable Long resumeId,
            @RequestHeader("X-User-Id") Long candidateId
    ) throws Exception {
        return  ResponseEntity.ok(resumeService.setDefaultResume(resumeId, candidateId));
    }

    @DeleteMapping("/{resumeId}")
    public ResponseEntity<ApiMessage> deleteResume(
            @PathVariable Long resumeId,
            @RequestHeader("X-User-Id") Long candidateId
    ) throws Exception {
        
        resumeService.deleteResume(resumeId,candidateId);

        return ResponseEntity.ok(new ApiMessage("Resume Deleted Successfully", true));
    }


}

package com.samrit.job.controller;


import com.samrit.job.dto.ApiMessage;
import com.samrit.job.dto.JobRequest;
import com.samrit.job.dto.JobResponse;
import com.samrit.job.payload.JobSearchRequest;
import com.samrit.job.service.JobService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService jobService;

    @PostMapping
    public ResponseEntity<JobResponse>createJob(
            @RequestBody @Valid JobRequest jobRequest,
            @RequestHeader ("X-User-Id") Long employerId
            ) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(jobService.createJob(employerId, jobRequest));
    }
    @GetMapping("/{id}")
    public ResponseEntity<JobResponse> getJobById(
            @PathVariable Long id
    ) throws Exception {
        return ResponseEntity.ok(jobService.getJobById(id));
    }
    @GetMapping
    public ResponseEntity<List<JobResponse>> getJobs(
            @ModelAttribute JobSearchRequest req
            )  {
        return ResponseEntity.ok(jobService.getJobs(req));
    }
    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<JobResponse>> getJobsByCompanyId(
            @PathVariable Long companyId
    )  {
        return ResponseEntity.ok(jobService.getJobsByCompany(companyId));
    }
    @GetMapping("/admin")
    public ResponseEntity<List<JobResponse>> getAllJobsAdmin()  {
        return ResponseEntity.ok(jobService.getAllJobsAdmin());
    }
    @PutMapping("/{id}")
    public ResponseEntity<JobResponse> updateJob(
            @PathVariable Long id,
            @RequestHeader("X-User-Id") Long employerId,
            @RequestBody @Valid JobRequest request
    ) throws Exception {
        return  ResponseEntity.ok(jobService.updateJob(id,employerId,request));
    }
    @PatchMapping("/{id}/publish")
    public ResponseEntity<JobResponse>publishJob(
            @PathVariable Long id,
            @RequestHeader("X-User-Id") Long employerId
    ) throws Exception {
        return  ResponseEntity.ok(jobService.publishJob(id, employerId));
    }
    @PatchMapping("/{id}/close")
    public ResponseEntity<JobResponse>closeJob(
            @PathVariable Long id,
            @RequestHeader("X-User-Id") Long employerId
    ) throws Exception {
        return  ResponseEntity.ok(jobService.closeJob(id, employerId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiMessage>DeleteJob(
            @PathVariable Long id,
            @RequestHeader("X-User-Id") Long employerId
    ) throws Exception {
        jobService.deleteJob(id, employerId);
        return  ResponseEntity.ok(new ApiMessage("Job Deleted Successfully",true));
    }


}

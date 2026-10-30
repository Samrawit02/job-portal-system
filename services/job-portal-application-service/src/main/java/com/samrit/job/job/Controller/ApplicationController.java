package com.samrit.job.job.Controller;

import com.samrit.job.dto.ApiMessage;
import com.samrit.job.dto.ApplicationResponse;
import com.samrit.job.job.model.Application;
import com.samrit.job.job.payload.CompanyApplicationFilterRequest;
import com.samrit.job.job.payload.CreateApplicationRequest;
import com.samrit.job.job.payload.UpdateApplicationStatusRequest;
import com.samrit.job.job.payload.WithdrawApplicationRequest;
import com.samrit.job.job.service.ApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.bouncycastle.pqc.jcajce.provider.NTRU;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping
    public ResponseEntity<ApplicationResponse> createApplication(
            @RequestHeader("X-User-Id") Long candidateId,
            @RequestBody CreateApplicationRequest application) throws Exception {

        return ResponseEntity.ok(
                applicationService.createApplication(candidateId, application));
    }
    @GetMapping("{/id}")
    public ResponseEntity<ApplicationResponse>getApplicationById(
            @PathVariable Long id) throws Exception {
        return ResponseEntity.ok(applicationService.getApplicationById(id));
    }
    @GetMapping("{/my}")
    public ResponseEntity<ApplicationResponse>getMyApplicationById(
            @RequestHeader("X-User-Id") Long candidateId) throws Exception {
        return ResponseEntity.ok(applicationService.getApplicationById(candidateId));
    }

    @GetMapping("/job/{jobId}")
    public ResponseEntity<List<ApplicationResponse>> getApplicationForJob(
            @PathVariable Long jobId){
        return ResponseEntity.ok(applicationService.getApplicationForJob(jobId));
    }

    @GetMapping("company")
    public ResponseEntity<List<ApplicationResponse>> getApplicationForCompany(
            @RequestHeader("X-User-Id") Long ownerId,
            @ModelAttribute CompanyApplicationFilterRequest filterRequest
            ) {
        return  ResponseEntity.ok(applicationService.getApplicationForCompany(
                ownerId, filterRequest ));
    }
    @PatchMapping("/{id}/status")
    public ResponseEntity<ApplicationResponse> updateStatus(
            @PathVariable Long id,
            @RequestHeader("X-User-Id") Long employerId,
            @RequestBody @Valid UpdateApplicationStatusRequest request) throws Exception {
        return ResponseEntity.ok(applicationService.updateStatus(id, employerId,request));
    }
    @PatchMapping("/{id}/withdraw")
    public ResponseEntity<ApplicationResponse> withdraw(
            @PathVariable Long id,
            @RequestHeader("X-User-Id") Long employerId,
            @RequestBody @Valid WithdrawApplicationRequest request) throws Exception {
        return ResponseEntity.ok(applicationService.withdraw(id, employerId,request));
    }
    @PatchMapping("/{id}/star")
    public ResponseEntity<ApplicationResponse> toggleStar(
            @PathVariable Long id,
            @RequestHeader("X-User-Id") Long employerId) throws Exception {
        return  ResponseEntity.ok(applicationService.toggleStar(id, employerId));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiMessage> deleteApplication(
            @PathVariable Long id,
            @RequestHeader("X-User-Id") Long candidateId) throws Exception {
        applicationService.deleteApplication(id, candidateId);
        return ResponseEntity.ok(
                new ApiMessage("Application Deleted Successfully", true));
    }

}

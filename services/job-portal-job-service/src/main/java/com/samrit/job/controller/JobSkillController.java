package com.samrit.job.controller;

import com.samrit.job.dto.ApiMessage;
import com.samrit.job.dto.JobSkillResponse;
import com.samrit.job.payload.JobSkillRequest;
import com.samrit.job.service.JobSkillService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/job-skills")
public class JobSkillController {

    private final JobSkillService jobSkillService;

    @PostMapping
    public ResponseEntity<JobSkillResponse> createSkill(
            @RequestBody @Valid JobSkillRequest request
            ) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(jobSkillService.createSkill(request));
    }
    @GetMapping
    public ResponseEntity<List<JobSkillResponse>> getAllSkills(){
        return ResponseEntity.ok(jobSkillService.getAllSkills());
    }
    @GetMapping("/{id}")
    public ResponseEntity<JobSkillResponse> getSkillById(@PathVariable Long id) throws Exception {

        return ResponseEntity.ok(jobSkillService.getSkillById(id));
    }

    @PostMapping("/{id}")
    public ResponseEntity<JobSkillResponse> updateSkill(
            @PathVariable Long id,
            @RequestBody @Valid JobSkillRequest request
    ) throws Exception {
        return ResponseEntity.ok(jobSkillService.updateSkill(id,request));
    }
    @DeleteMapping("/{id}")
    public  ResponseEntity<ApiMessage> deleteSkill(
            @PathVariable Long id
    ) throws Exception {
        jobSkillService.deleteSkill(id);
        return ResponseEntity.ok(new ApiMessage("Skill deleted successfully", true));
    }
}

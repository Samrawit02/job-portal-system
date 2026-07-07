package com.samrit.job.controller;

import com.samrit.job.dto.ApiMessage;
import com.samrit.job.dto.JobTagResponse;
import com.samrit.job.payload.JobTagRequest;
import com.samrit.job.service.JobTagService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/job-tags")
public class JobTagController {

    private final JobTagService jobTagService;

    @PostMapping
    public ResponseEntity<JobTagResponse> createJobTag(
            @RequestBody @Valid JobTagRequest request
            ) throws Exception {
        return  ResponseEntity.status(HttpStatus.CREATED)
                .body(jobTagService.createTag(request));
    }
    @GetMapping()
    public  ResponseEntity<List<JobTagResponse>> getAllTagsById() {
        return  ResponseEntity.ok(jobTagService.getAllTags());
    }
    @GetMapping("/{id}")
    public  ResponseEntity<JobTagResponse> getTagById(
            @PathVariable Long id
    ) throws Exception {
        return  ResponseEntity.ok(jobTagService.getById(id));
    }

    @PutMapping("/{id}")
    public  ResponseEntity<JobTagResponse> updateTag(
            @PathVariable Long id,
            @RequestBody @Valid JobTagRequest request
    ) throws Exception {
        return  ResponseEntity.ok(jobTagService.updateTag(id, request));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiMessage> deleteTag(
            @PathVariable Long id
    ) throws Exception {
        jobTagService.deleteJogTag(id);
        return ResponseEntity.ok(new ApiMessage("Tag Deleted Successfully", true));
    }
}

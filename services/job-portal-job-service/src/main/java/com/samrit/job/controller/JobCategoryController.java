package com.samrit.job.controller;


import com.samrit.job.dto.ApiMessage;
import com.samrit.job.dto.JobResponse;
import com.samrit.job.payload.JobCategoryRequest;
import com.samrit.job.service.JobCategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/job-categories")
public class JobCategoryController {

    private final JobCategoryService jobCategoryService;

    @PostMapping
    public ResponseEntity<JobResponse.JobCategoryResponse> createCategory(
            @RequestBody @Valid JobCategoryRequest request
            ) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(jobCategoryService.createJobCategory(request));

    }

    @GetMapping
    public  ResponseEntity<List<JobResponse.JobCategoryResponse>> getAllCategories(){
        return ResponseEntity.ok(jobCategoryService.getAllCategories());

    }
    @GetMapping("/{id}")
    public ResponseEntity<JobResponse.JobCategoryResponse>getCategoryById(
            @PathVariable Long id
    ) throws Exception {
        return  ResponseEntity.ok(jobCategoryService.getCategoryById(id));

    }
    @PutMapping("/{id}")
    public ResponseEntity<JobResponse.JobCategoryResponse> updateCategory(
            @PathVariable Long id,
            @RequestBody @Valid JobCategoryRequest request

            ) throws Exception {
        return  ResponseEntity.ok(jobCategoryService.updateCategory(id,request));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiMessage> deleteCategory(
            @PathVariable Long id
    ) throws Exception {
        jobCategoryService.deleteCategory(id);
        return ResponseEntity.ok(new ApiMessage("Category Deleted Successfully", true));
    }
}

package com.samrit.job.service;

import com.samrit.job.dto.JobResponse;
import com.samrit.job.model.JobCategory;
import com.samrit.job.payload.JobCategoryRequest;

import java.util.List;

public interface JobCategoryService {
    JobResponse.JobCategoryResponse createJobCategory (JobCategoryRequest request) throws Exception;
    List<JobResponse.JobCategoryResponse> getAllCategories();
    JobResponse.JobCategoryResponse getCategoryById(Long id) throws Exception;
    JobResponse.JobCategoryResponse updateCategory(Long id, JobCategoryRequest req) throws Exception;
    void  deleteCategory(Long id) throws Exception;
    JobCategory getCategoryEntityById(Long id) throws Exception;

}

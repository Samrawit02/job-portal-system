package com.samrit.job.service;

import com.samrit.job.dto.JobRequest;
import com.samrit.job.dto.JobResponse;
import com.samrit.job.payload.JobSearchRequest;

import java.util.List;

public interface JobService {
    JobResponse createJob(Long employerId, JobRequest jobRequest) throws Exception;
    JobResponse getJobById(Long id) throws Exception;
    JobResponse updateJob(Long jobId, Long employerId, JobRequest req) throws Exception;
    List<JobResponse> getJobs(JobSearchRequest request);
    List<JobResponse> getJobsByCompany(Long companyId);
    JobResponse publishJob(Long jobId, Long employId ) throws Exception;
    JobResponse closeJob(Long jobId, Long employId ) throws Exception;
    void deleteJob(Long jobId, Long employId ) throws Exception;
    List<JobResponse>getAllJobsAdmin();

}

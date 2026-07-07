package com.samrit.job.service;

import com.samrit.job.dto.JobTagResponse;
import com.samrit.job.model.JobTag;
import com.samrit.job.payload.JobTagRequest;

import java.util.List;
import java.util.Set;

public interface JobTagService {

    JobTagResponse createTag(JobTagRequest request) throws Exception;
    List<JobTagResponse>getAllTags();
    JobTagResponse getById(Long id) throws Exception;
    JobTagResponse updateTag(Long id, JobTagRequest req) throws Exception;
    void deleteJogTag(Long id) throws Exception;
    JobTag getTagEntityById(Long id) throws Exception;
    Set<JobTag> getTagsByIds(Set<Long>ids) throws Exception;

}

package com.samrit.job.service.impl;

import com.samrit.job.Repo.JobTagRepo;
import com.samrit.job.dto.JobTagResponse;
import com.samrit.job.mapper.JobTagMapper;
import com.samrit.job.model.JobTag;
import com.samrit.job.payload.JobTagRequest;
import com.samrit.job.service.JobTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobTagServiceImpl implements JobTagService {

    private final JobTagRepo jobTagRepo;

    @Override
    public JobTagResponse createTag(JobTagRequest request) throws Exception {
        if(jobTagRepo.existsByName(request.getName())){
            throw new Exception("tag name already exist");
        }
        String slug =  generateUniqueSlug(request.getName());

        JobTag tag = JobTag.builder()
                .name(request.getName())
                .slug(slug)
                .createdAt(LocalDateTime.now())
                .build();
        return JobTagMapper.toTagResponse(jobTagRepo.save(tag));
    }

    @Override
    public List<JobTagResponse> getAllTags() {
        return jobTagRepo.findAll()
                .stream()
                .map(JobTagMapper::toTagResponse)
                .collect(Collectors.toList());
    }

    @Override
    public JobTagResponse getById(Long id) throws Exception {
        return JobTagMapper.toTagResponse(getTagEntityById(id));
    }

    @Override
    public JobTagResponse updateTag(Long id, JobTagRequest req) throws Exception {

        JobTag tag = getTagEntityById(id);
        if(!tag.getName().equals(req.getName())
            && jobTagRepo.existsByName(req.getName())){
                throw  new Exception("tag name already exist");
        }
        tag.setName(req.getName());
        return JobTagMapper.toTagResponse(jobTagRepo.save(tag));

    }

    @Override
    public void deleteJogTag(Long id) throws Exception {
        jobTagRepo.delete( getTagEntityById(id));

    }

    @Override
    public JobTag getTagEntityById(Long id) throws Exception {
        return jobTagRepo.findById(id).orElseThrow(
                ()-> new Exception("Job tag not found")
        );
    }

    @Override
    public Set<JobTag> getTagsByIds(Set<Long> ids) {
        return new HashSet<>(jobTagRepo.findAllById(ids)) ;
    }

    private String generateUniqueSlug(String name) {
        String base = name.toLowerCase()
                .replaceAll("[^a-z0-9\\s-]", "").trim()
                .replaceAll("[\\s-]+", "-");
        if(!jobTagRepo.existsBySlug(base)){
            return  base;
        }
        int counter = 1;
        while (jobTagRepo.existsBySlug(base+"-"+counter)){
            counter++;
        }
        return base +"-"+counter;
    }
}

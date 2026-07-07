package com.samrit.job.service.impl;

import com.samrit.job.Repo.JobCategoryRepository;
import com.samrit.job.dto.JobResponse;
import com.samrit.job.mapper.JobCategoryMapper;
import com.samrit.job.model.JobCategory;
import com.samrit.job.payload.JobCategoryRequest;
import com.samrit.job.service.JobCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class JobCategoryServiceImpl implements JobCategoryService {

    private final JobCategoryRepository jobCategoryRepository;

    @Override
    public JobResponse.JobCategoryResponse createJobCategory(JobCategoryRequest request) throws Exception {

        if(jobCategoryRepository.existsByName(request.getName())){
            throw  new Exception("Category name already exist, use different name");
        }
        JobCategory parent= null;
        if(request.getParentId()!=null){
            parent = getCategoryEntityById(request.getParentId());
        }
        String slug = generateUniqueSlug(request.getName());

        JobCategory category = JobCategory.builder()
                .name(request.getName())
                .slug(slug)
                .description(request.getDescription())
                .iconUrl(request.getIconUrl())
                .parent(parent)
                .active(true)
                .build();

        return JobCategoryMapper.toJobCategoryResponse(jobCategoryRepository.save(category), true);
    }

    @Override
    public List<JobResponse.JobCategoryResponse> getAllCategories() {
        return jobCategoryRepository.findByActiveTrue()
                .stream()
                .map(c-> JobCategoryMapper.toJobCategoryResponse(c,false))
                .collect(Collectors.toList());
    }

    @Override
    public JobResponse.JobCategoryResponse getCategoryById(Long id) throws Exception {
        JobCategory jobCategory = getCategoryEntityById(id);
        return JobCategoryMapper.toJobCategoryResponse(jobCategory, true);
    }

    @Override
    public JobResponse.JobCategoryResponse updateCategory(Long id, JobCategoryRequest req) throws Exception {
       JobCategory category = getCategoryEntityById(id);

       if(category.getName().equals(req.getName()) &&
       jobCategoryRepository.existsByName(req.getName())){
           throw new Exception("Category name already exist, choose different name");
       }
       JobCategory parent = null;
       if(req.getParentId()!=null){
           if(req.getParentId().equals(id)){
               throw new Exception("A category cannot be its own parent");
           }
           parent = getCategoryEntityById(req.getParentId());
       }
       category.setName(req.getName());
       category.setDescription(req.getDescription());
       category.setIconUrl(req.getIconUrl());
       category.setParent(parent);

        return  JobCategoryMapper.toJobCategoryResponse(jobCategoryRepository.save(category),true);
    }

    @Override
    public void deleteCategory(Long id) throws Exception {
        JobCategory category = getCategoryEntityById(id);
        category.setActive(false);
        jobCategoryRepository.save(category);


    }

    @Override
    public JobCategory getCategoryEntityById(Long id) throws Exception {
        return jobCategoryRepository.findById(id).orElseThrow(
                ()-> new Exception("Category not found")
        );
    }
    private String generateUniqueSlug(String name) {
        String base = name.toLowerCase()
                .replaceAll("[^a-z0-9\\s-]", "").trim()
                .replaceAll("[\\s-]+", "-");
        if(!jobCategoryRepository.existsBySlug(base)){
            return  base;
        }
        int counter = 1;
        while (jobCategoryRepository.existsBySlug(base+"-"+counter)){
            counter++;
        }
        return base +"-"+counter;
    }

}

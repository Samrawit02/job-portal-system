package com.samrit.job.service.impl;

import com.samrit.job.Repo.JobSkillRepo;
import com.samrit.job.dto.JobSkillResponse;
import com.samrit.job.mapper.JobSkillMapper;
import com.samrit.job.model.JobSkill;
import com.samrit.job.payload.JobSkillRequest;
import com.samrit.job.service.JobSkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class JobSkillServiceImpl implements JobSkillService {

    private final JobSkillRepo jobSkillRepo;

    @Override
    public JobSkillResponse createSkill(JobSkillRequest request) throws Exception {

        if(jobSkillRepo.existsByName(request.getName())){
            throw new Exception("Skill Name already exists");
        }

        String slug = generateUniqueSlug(request.getName());
        JobSkill skill = JobSkill.builder()
                .name(request.getName())
                .slug(slug)
                .category(request.getSkillCategory())
                .active(true)
                .build();
        return JobSkillMapper.toJobSkillResponse(jobSkillRepo.save(skill));
    }

    @Override
    public List<JobSkillResponse> getAllSkills() {
        return jobSkillRepo.findByActiveTrue().stream()
                .map(JobSkillMapper::toJobSkillResponse)
                .collect(Collectors.toList());
    }

    @Override
    public JobSkillResponse getSkillById(Long id) throws Exception {
        return JobSkillMapper.toJobSkillResponse(jobSkillRepo.findById(id).orElseThrow(()
                -> new Exception("Skill not found")));
    }

    @Override
    public JobSkillResponse updateSkill(Long id, JobSkillRequest request) throws Exception {
       JobSkill skill =  jobSkillRepo.findById(id).orElseThrow(()
                -> new Exception("Skill not found"));

        if (!skill.getName().equals(request.getName())
                && jobSkillRepo.existsByName(request.getName())
        ) {
            throw new Exception("Skill name already exists");

        }
        skill.setName(request.getName());
        skill.setCategory(request.getSkillCategory());
        return JobSkillMapper.toJobSkillResponse(jobSkillRepo.save(skill));
    }

    @Override
    public void deleteSkill(Long id) throws Exception {

        JobSkill skill =  jobSkillRepo.findById(id).orElseThrow(()
                -> new Exception("Skill not found"));
        skill.setActive(false);
        jobSkillRepo.delete(skill);
    }

    @Override
    public Set<JobSkill> getSkillsByIds(Set<Long> ids) {
        return new HashSet<>(jobSkillRepo.findAllById(ids));
    }

    private String generateUniqueSlug(String name) {
        String base = name.toLowerCase()
                .replaceAll("[^a-z0-9\\s-]", "").trim()
                .replaceAll("[\\s-]+", "-");
        if(!jobSkillRepo.existsBySlug(base)){
            return  base;
        }
        int counter = 1;
        while (jobSkillRepo.existsBySlug(base+"-"+counter)){
            counter++;
        }
        return base +"-"+counter;
    }
}

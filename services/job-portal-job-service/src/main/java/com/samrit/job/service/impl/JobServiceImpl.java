package com.samrit.job.service.impl;

import com.samrit.job.Repo.JobSpecification;
import com.samrit.job.domain.JobStatus;
import com.samrit.job.dto.CompanyResponse;
import com.samrit.job.mapper.JobMapper;
import com.samrit.job.Repo.JobRepo;
import com.samrit.job.dto.JobRequest;
import com.samrit.job.dto.JobResponse;
import com.samrit.job.model.Job;
import com.samrit.job.model.JobCategory;
import com.samrit.job.model.JobSkill;
import com.samrit.job.model.JobTag;
import com.samrit.job.model.embeddable.JobLocation;
import com.samrit.job.model.embeddable.SalaryRange;
import com.samrit.job.payload.JobSearchRequest;
import com.samrit.job.service.JobCategoryService;
import com.samrit.job.service.JobService;
import com.samrit.job.service.JobSkillService;
import com.samrit.job.service.JobTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobRepo jobRepo;
    private final JobCategoryService categoryService;
    private final JobSkillService skillService;
    private final JobTagService tagService;

    @Override
    public JobResponse createJob(Long employerId, JobRequest jobRequest) throws Exception {

        JobCategory category = categoryService.getCategoryEntityById(jobRequest.getCategoryId());
        Set<JobSkill> skills =jobRequest.getSkillIds()!=null ?
                skillService.getSkillsByIds(jobRequest.getSkillIds())
                : Collections.emptySet();
        Set<JobTag> tags = jobRequest.getTagIds()!=null ?
                tagService.getTagsByIds(jobRequest.getTagIds())
                : Collections.emptySet();
// todo: fetch company by employ id
        Long companyId= 1L;

        Job job = Job.builder()
                .title(jobRequest.getTitle())
                .description(jobRequest.getDescription())
                .requirements(jobRequest.getRequirement())
                .responsibilities(jobRequest.getResponsibilities())
                .benefits(jobRequest.getBenefits())
                .companyId(companyId)
                .employerId(employerId)
                .category(category)
                .skills(skills)
                .tags(tags)
                .location(buildLocation(jobRequest))
                .salaryRange(buildSalaryRange(jobRequest))
                .jobType(jobRequest.getJobType())
                .workMode(jobRequest.getWorkMode())
                .experienceLevel(jobRequest.getExperienceLevel())
                .openings(jobRequest.getOpenings()!=null ? jobRequest.getOpenings(): 1)
                .applicationDeadline(jobRequest.getApplicationDeadline())
                .expiresAt(jobRequest.getExpiresAt())
                .active(true)
                .status(JobStatus.DRAFT)
                .build();
        Job saveJob= jobRepo.save(job);
        return convertToJobResponse(job);

    }

    private SalaryRange buildSalaryRange(JobRequest jobRequest) {
        return SalaryRange.builder()
                .maxSalary(jobRequest.getMaxSalary())
                .minSalary(jobRequest.getMinSalary())
                .build();

    }

    private JobLocation buildLocation(JobRequest jobRequest) {
        return JobLocation.builder()
                .address(jobRequest.getAddress())
                .city(jobRequest.getCity())
                .state(jobRequest.getState())
                .country(jobRequest.getCountry())
                .zipCode(jobRequest.getZipCode())
                .build();
    }

    @Override
    public JobResponse getJobById(Long id) throws Exception {
        Job job =  jobRepo.findById(id).orElseThrow(
                ()-> new Exception ("Job not found")
        );
        return convertToJobResponse(job);
    }

    @Override
    public JobResponse updateJob(Long jobId, Long employerId, JobRequest req) throws Exception {

        JobCategory category = categoryService.getCategoryEntityById(req.getCategoryId());
        Set<JobSkill> skills =req.getSkillIds()!=null ?
                skillService.getSkillsByIds(req.getSkillIds())
                : Collections.emptySet();
        Set<JobTag> tags = req.getTagIds()!=null ?
                tagService.getTagsByIds(req.getSkillIds())
                : Collections.emptySet();

        Job job = jobRepo.findById(jobId).orElseThrow(
                ()-> new Exception("Job not found")
        );
        assertEmployer(job, employerId);
        job.setTitle(req.getTitle());
        job.setDescription(req.getDescription());
        job.setRequirements(req.getRequirement());
        job.setResponsibilities(req.getResponsibilities());
        job.setBenefits(req.getBenefits());
        job.setCategory(category);
        job.setSkills(skills);
        job.setTags(tags);
        job.setLocation(buildLocation(req));
        job.setSalaryRange(buildSalaryRange(req));
        job.setJobType(req.getJobType());
        job.setWorkMode(req.getWorkMode());
        job.setExperienceLevel(req.getExperienceLevel());
        job.setOpenings(req.getOpenings()!=null ? req.getOpenings(): 1);
        job.setApplicationDeadline(req.getApplicationDeadline());
        job.setExpiresAt(req.getExpiresAt());

        return convertToJobResponse(jobRepo.save(job));
    }

    @Override
    public List<JobResponse> getJobs(JobSearchRequest request) {
        List<Job> jobs = jobRepo.findAll(JobSpecification.build(request));
        return jobs.stream()
                .map(JobServiceImpl::convertToJobResponse).collect(Collectors.toList());
    }

    @Override
    public List<JobResponse> getJobsByCompany(Long companyId) {
        List<Job> jobs = jobRepo.findByCompanyId(companyId);
        return jobs.stream()
                .map(JobServiceImpl::convertToJobResponse).collect(Collectors.toList());
    }

    @Override
    public JobResponse publishJob(Long jobId, Long employId) throws Exception {
        Job job = jobRepo.findById(jobId).orElseThrow(
                ()-> new Exception("Job not found")
        );
        assertEmployer(job, employId);
        if(job.getStatus()== JobStatus.CLOSED || job.getStatus()== JobStatus.EXPIRED)
            throw   new Exception("Job is expired");
        job.setActive(true);
        job.setStatus(JobStatus.OPEN);
        job.setPublishedAt(LocalDateTime.now());
        return convertToJobResponse(jobRepo.save(job));

    }
    @Override
    public JobResponse closeJob(Long jobId, Long employId) throws Exception {
        Job job = jobRepo.findById(jobId).orElseThrow(
                ()-> new Exception("Job not found")
        );
        assertEmployer(job, employId);
        job.setActive(false);
        job.setStatus(JobStatus.CLOSED);
        job.setClosedAt(LocalDateTime.now());
        return convertToJobResponse(jobRepo.save(job));

    }

    @Override
    public void deleteJob(Long jobId, Long employId) throws Exception {
        Job job = jobRepo.findById(jobId).orElseThrow(
                ()-> new Exception("Job not found")
        );
        assertEmployer(job, employId);
        jobRepo.delete(job);
    }

    @Override
    public List<JobResponse> getAllJobsAdmin() {
        return jobRepo.findAll()
                .stream()
                .map(JobServiceImpl::convertToJobResponse)
                .collect(Collectors.toList());
    }

    public static JobResponse convertToJobResponse(Job savedJob) {

        // todo: fetch company response
        CompanyResponse companyResponse = CompanyResponse.builder()
                .id(savedJob.getCompanyId())
                .build();

        return JobMapper.ToJobResponse(savedJob, companyResponse);
    }

    private void assertEmployer(Job job, Long employId) throws Exception {
        if (!job.getEmployerId().equals(employId)) {
            throw new Exception("You are not the employer who posted this job");
        }
    }
}

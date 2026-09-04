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
import com.samrit.job.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobRepo jobRepo;
    private final JobCategoryService categoryService;
    private final JobSkillService skillService;
    private final JobTagService tagService;
    private final CompanyService companyService;

    @Override
    public JobResponse createJob(Long employerId, JobRequest jobRequest) throws Exception {

        JobCategory category = categoryService.getCategoryEntityById(jobRequest.getCategoryId());
        Set<JobSkill> skills = jobRequest.getSkillIds() != null ? skillService.getSkillsByIds(jobRequest.getSkillIds())
                : Collections.emptySet();
        Set<JobTag> tags = jobRequest.getTagIds() != null ? tagService.getTagsByIds(jobRequest.getTagIds())
                : Collections.emptySet();

        Long companyId = jobRequest.getCompanyId();
        if (companyId == null) {
            CompanyResponse companyResponse = fetchCompanyProfileSafely(employerId);
            if (companyResponse != null) {
                companyId = companyResponse.getId();
            }
        } else {
            CompanyResponse companyResponse = companyService.getCompanyById(companyId);
            if (companyResponse == null || !Objects.equals(companyResponse.getOwnerId(), employerId)) {
                throw new Exception("You are not authorized to use this company");
            }
        }
        if (companyId == null) {
            throw new Exception(
                    "Company profile not found for employer. Please create a company profile or specify 'companyId' in the request before posting a job.");
        }

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
                .openings(jobRequest.getOpenings() != null ? jobRequest.getOpenings() : 1)
                .applicationDeadline(jobRequest.getApplicationDeadline())
                .expiresAt(jobRequest.getExpiresAt())
                .active(true)
                .status(JobStatus.DRAFT)
                .build();
        return convertToJobResponse(jobRepo.save(job));
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
        Job job = jobRepo.findByIdAndActiveTrueAndStatus(id, JobStatus.OPEN).orElseThrow(
                () -> new Exception("Job not found"));
        return convertToJobResponse(job);
    }

    @Override
    public JobResponse updateJob(Long jobId, Long employerId, JobRequest req) throws Exception {

        JobCategory category = categoryService.getCategoryEntityById(req.getCategoryId());
        Set<JobSkill> skills = req.getSkillIds() != null ? skillService.getSkillsByIds(req.getSkillIds())
                : Collections.emptySet();
        Set<JobTag> tags = req.getTagIds() != null ? tagService.getTagsByIds(req.getTagIds())
                : Collections.emptySet();

        Job job = jobRepo.findById(jobId).orElseThrow(
                () -> new Exception("Job not found"));
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
        job.setOpenings(req.getOpenings() != null ? req.getOpenings() : 1);
        job.setApplicationDeadline(req.getApplicationDeadline());
        job.setExpiresAt(req.getExpiresAt());

        return convertToJobResponse(jobRepo.save(job));
    }

    @Override
    public List<JobResponse> getJobs(JobSearchRequest request) {
        List<Job> jobs = jobRepo.findAll(JobSpecification.build(request));
        return convertToJobResponses(jobs);
    }

    @Override
    public List<JobResponse> getJobsByCompany(Long companyId) {
        List<Job> jobs = jobRepo.findByCompanyIdAndActiveTrueAndStatus(companyId, JobStatus.OPEN);
        return convertToJobResponses(jobs);
    }

    @Override
    public JobResponse publishJob(Long jobId, Long employerId) throws Exception {
        Job job = jobRepo.findById(jobId).orElseThrow(
                () -> new Exception("Job not found"));
        assertEmployer(job, employerId);
        if (job.getStatus() == JobStatus.CLOSED || job.getStatus() == JobStatus.EXPIRED)
            throw new Exception("Job is expired");
        job.setActive(true);
        job.setStatus(JobStatus.OPEN);
        job.setPublishedAt(LocalDateTime.now());
        return convertToJobResponse(jobRepo.save(job));

    }

    @Override
    public JobResponse closeJob(Long jobId, Long employerId) throws Exception {
        Job job = jobRepo.findById(jobId).orElseThrow(
                () -> new Exception("Job not found"));
        assertEmployer(job, employerId);
        job.setActive(false);
        job.setStatus(JobStatus.CLOSED);
        job.setClosedAt(LocalDateTime.now());
        return convertToJobResponse(jobRepo.save(job));

    }

    @Override
    public void deleteJob(Long jobId, Long employerId) throws Exception {
        Job job = jobRepo.findById(jobId).orElseThrow(
                () -> new Exception("Job not found"));
        assertEmployer(job, employerId);
        jobRepo.delete(job);
    }

    @Override
    public List<JobResponse> getAllJobsAdmin() {
        List<Job> jobs = jobRepo.findAll();
        return convertToJobResponses(jobs);
    }

    public JobResponse convertToJobResponse(Job savedJob) {
        CompanyResponse companyResponse = fetchCompanyProfileSafely(savedJob.getEmployerId());
        if (companyResponse == null && savedJob.getCompanyId() != null) {
            companyResponse = CompanyResponse.builder()
                    .id(savedJob.getCompanyId())
                    .build();
        }
        return JobMapper.ToJobResponse(savedJob, companyResponse);
    }

    private List<JobResponse> convertToJobResponses(List<Job> jobs) {
        if (jobs == null || jobs.isEmpty()) {
            return Collections.emptyList();
        }
        Set<Long> employerIds = jobs.stream()
            .map(job -> job.getEmployerId())
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        Map<Long, CompanyResponse> companyMap = employerIds.stream()
                .collect(Collectors.toMap(
                        id -> id,
                        this::fetchCompanyProfileSafely,
                        (existing, replacement) -> existing));

        return jobs.stream()
                .map(job -> {
                    CompanyResponse company = job.getEmployerId() != null
                            ? companyMap.get(job.getEmployerId())
                            : null;
                    if (company == null && job.getCompanyId() != null) {
                        company = CompanyResponse.builder().id(job.getCompanyId()).build();
                    }
                    return JobMapper.ToJobResponse(job, company);
                })
                .collect(Collectors.toList());
    }

    private CompanyResponse fetchCompanyProfileSafely(Long employerId) {
        if (employerId == null) {
            return null;
        }
        try {
            CompanyResponse response = companyService.getCompanyProfile(employerId);
            return (response != null && response.getId() != null) ? response : null;
        } catch (Exception e) {
            return null;
        }
    }

    private void assertEmployer(Job job, Long employerId) throws Exception {
        if (!job.getEmployerId().equals(employerId)) {
            throw new Exception("You are not the employer who posted this job");
        }
    }
}

package com.samrit.job.Repo;

import com.samrit.job.domain.JobStatus;
import com.samrit.job.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobRepo extends JpaRepository<Job, Long>, JpaSpecificationExecutor<Job> {
    List<Job> findByCompanyId(Long companyId);
    Optional<Job> findByIdAndActiveTrueAndStatus(Long id, JobStatus status);
    List<Job> findByCompanyIdAndActiveTrueAndStatus(Long companyId, JobStatus status);
}

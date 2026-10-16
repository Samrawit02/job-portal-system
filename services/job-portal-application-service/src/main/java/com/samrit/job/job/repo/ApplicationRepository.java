package com.samrit.job.job.repo;

import com.samrit.job.job.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByCandidateId(Long candidateId);
    List<Application> findByJobId(Long jobId);
    boolean existByCandidateIdAndJobId(Long candidateId, Long jobId);
}

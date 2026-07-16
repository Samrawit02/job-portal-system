package com.samrit.job.repo;

import com.samrit.job.model.Education;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EducationRepo extends JpaRepository<Education, Long> {
    List<Education> findByResume_IdAndOrderByDisplayOrderAsc(Long resumeId);
}

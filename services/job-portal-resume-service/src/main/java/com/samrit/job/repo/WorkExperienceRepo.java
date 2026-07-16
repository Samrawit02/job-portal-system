package com.samrit.job.repo;

import com.samrit.job.model.WorkExperience;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkExperienceRepo  extends JpaRepository<WorkExperience, Long> {

    List<WorkExperience> findByResume_IdOrderByDisplayOrderAsc(Long resumeId);
}

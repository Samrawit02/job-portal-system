package com.samrit.job.repo;

import com.samrit.job.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepo extends JpaRepository<Project, Long> {
    List<Project> findByResume_IdAndOrderByDisplayOrderAsc(Long resumeId);
}

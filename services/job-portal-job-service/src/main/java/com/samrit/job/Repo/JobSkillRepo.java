package com.samrit.job.Repo;

import com.samrit.job.model.JobSkill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobSkillRepo extends JpaRepository<JobSkill, Long> {
    List<JobSkill> findByActiveTrue();
    boolean existsByName(String name);
    boolean existsBySlug(String slug);

}

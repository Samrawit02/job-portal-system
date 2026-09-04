package com.samrit.job.Repo;

import com.samrit.job.model.JobSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobSkillRepo extends JpaRepository<JobSkill, Long> {
    List<JobSkill> findByActiveTrue();

    boolean existsByName(String name);

    boolean existsBySlug(String slug);

}

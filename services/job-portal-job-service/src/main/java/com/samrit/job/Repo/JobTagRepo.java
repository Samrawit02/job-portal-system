package com.samrit.job.Repo;

import com.samrit.job.model.JobTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobTagRepo extends JpaRepository<JobTag , Long> {

    boolean existsByName(String name);
    boolean existsBySlug(String slug);
}

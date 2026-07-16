package com.samrit.job.repo;

import com.samrit.job.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LanguageRepo  extends JpaRepository<Language, Long> {
    List<Language> findByResume_IdAndOrderByDisplayOrderAsc(Long resumeId);
}

package com.samrit.job.service;

import com.samrit.job.dto.LanguageResponse;
import com.samrit.job.payload.LanguageRequest;

import java.util.List;

public interface LanguageService {
    LanguageResponse addLanguage (Long resumeId, Long candidateId, LanguageRequest request) throws Exception;
    List<LanguageResponse> getLanguages (Long resumeId);
    LanguageResponse updateLanguage (Long languageId, Long resumeId, Long candidateId, LanguageRequest request) throws Exception;
    void deleteLanguage(Long languageId, Long resumeId, Long candidateId) throws Exception;
}

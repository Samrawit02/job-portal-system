package com.samrit.job.service.impl;

import com.samrit.job.Mapper.ResumeMapper;
import com.samrit.job.dto.LanguageResponse;
import com.samrit.job.model.Language;
import com.samrit.job.model.Resume;
import com.samrit.job.payload.LanguageRequest;
import com.samrit.job.repo.LanguageRepo;
import com.samrit.job.service.LanguageService;
import com.samrit.job.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LanguageServiceImpl implements LanguageService {

    private final ResumeService resumeService;
    private final LanguageRepo languageRepo;

    @Override
    public LanguageResponse addLanguage(Long resumeId, Long candidateId, LanguageRequest request) throws Exception {
        Resume resume = resumeService.getResumeEntityById(resumeId);
        asserOwner(resume, candidateId);

        Language language = Language.builder()
                .languageName(request.getLanguageName())
                .resume(resume)
                .languageProficiency(request.getLanguageProficiency())
                .displayOrder(request.getDisplayOrder())
                .build();
        return ResumeMapper.toLanguageResponse(languageRepo.save(language));

    }

    @Override
    public List<LanguageResponse> getLanguages(Long resumeId) {
        return languageRepo.findByResume_IdAndOrderByDisplayOrderAsc(resumeId)
                .stream().map(
                        ResumeMapper::toLanguageResponse
                )
                .collect(Collectors.toList());
    }

    @Override
    public LanguageResponse updateLanguage(Long languageId, Long resumeId, Long candidateId, LanguageRequest request) throws Exception {
        Language language = languageRepo.findById(languageId)
                        .orElseThrow(()->
                                new Exception("Language not found"));

        asserOwner(language.getResume(), candidateId);

        language.setLanguageName(request.getLanguageName());
        language.setLanguageProficiency(request.getLanguageProficiency());
        if (request.getDisplayOrder() !=null) language.setDisplayOrder(request.getDisplayOrder());
        return ResumeMapper.toLanguageResponse(languageRepo.save(language));
    }

    @Override
    public void deleteLanguage(Long languageId, Long resumeId, Long candidateId) throws Exception {
        Language language = languageRepo.findById(languageId)
                .orElseThrow(()->
                        new Exception("Language not found"));

        asserOwner(language.getResume(), candidateId);
        languageRepo.delete(language);
    }
    private void asserOwner(Resume resume, Long candidateId) throws Exception {
        if(!resume.getCandidateId().equals(candidateId)){
            throw new Exception("resume not found with the given id");
        }
    }
}

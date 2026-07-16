package com.samrit.job.controller;


import com.samrit.job.dto.ApiMessage;
import com.samrit.job.dto.LanguageResponse;
import com.samrit.job.payload.LanguageRequest;
import com.samrit.job.service.LanguageService;
import com.samrit.job.service.impl.LanguageServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/resume/{resumeId}/language")
public class LanguageController {

    private final LanguageService languageService;

    @PostMapping
    public ResponseEntity<LanguageResponse> addLanguage(
            @PathVariable Long resumeId,
            @RequestHeader("X-User-Id") Long candidateId,
            @RequestBody @Valid LanguageRequest request
            ) throws Exception {
        return  ResponseEntity.status(HttpStatus.CREATED)
                .body(languageService.addLanguage(resumeId,candidateId,request));
    }

    @GetMapping
    public ResponseEntity<List<LanguageResponse> >getLanguages (
            @PathVariable Long resumeId
    ){
        return ResponseEntity.ok(languageService.getLanguages(resumeId));
    }
    @PutMapping("/{languageId}")
    public ResponseEntity<LanguageResponse> updateLanguages(
            @PathVariable  Long languageId,
            @PathVariable Long resumeId,
            @RequestHeader("X-User-Id") Long candidateId,
            @RequestBody @Valid LanguageRequest request
    ) throws Exception {
        return  ResponseEntity.ok(languageService.updateLanguage(languageId,resumeId,candidateId,request));
    }

    @DeleteMapping("/{languageId}")
    public ResponseEntity<ApiMessage> deleteLanguages(
            @PathVariable  Long languageId,
            @PathVariable Long resumeId,
            @RequestHeader("X-User-Id") Long candidateId
    ) throws Exception {
        languageService.deleteLanguage(languageId,resumeId,candidateId);
        return  ResponseEntity.ok(new ApiMessage("Language is deleted successfully", true));
    }

}

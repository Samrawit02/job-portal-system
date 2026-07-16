package com.samrit.job.service.impl;

import com.samrit.job.Mapper.ResumeMapper;
import com.samrit.job.dto.PersonalInfoResponse;
import com.samrit.job.dto.ResumeResponse;
import com.samrit.job.model.PersonalInfo;
import com.samrit.job.model.Resume;
import com.samrit.job.payload.ResumeRequest;
import com.samrit.job.repo.ResumeRepo;
import com.samrit.job.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepo resumeRepo;

    @Override
    public ResumeResponse createResume(Long candidateId, ResumeRequest request) {

        if(Boolean.TRUE.equals(request.getIsDefault())){
            resumeRepo.findByCandidateIdAndIsDefaultTrueAndIsActiveTrue(candidateId)
                    .ifPresent(existing -> {
                        existing.setIsDefault(false);
                        resumeRepo.save(existing);
                    });
        }
        Resume resume = Resume.builder()
                .candidateId(candidateId)
                .title(request.getTitle())
                .template(request.getTemplate())
                .visibility(request.getVisibility())
                .isDefault(Boolean.TRUE.equals(request.getIsDefault()))
                .isActive(true)
                .build();
        return buildFullResponse(resumeRepo.save(resume));
    }

    @Override
    public ResumeResponse getResumesById(Long resumeId, Long candidateId) throws Exception {
        Resume resume = getResumeEntityById(resumeId);
        asserOwner(resume, candidateId);

        return buildFullResponse(resume);
    }

    @Override
    public List<ResumeResponse> getMyResumes(Long candidateId) {
        return  resumeRepo.findByCandidateIdAndIsActiveTrue(candidateId)
                .stream()
                .map(this::buildFullResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ResumeResponse updatePersonalInformation(Long resumeId, Long candidateId, PersonalInfoResponse personalInfo) throws Exception {
        Resume resume = getResumeEntityById(resumeId);
        asserOwner(resume, candidateId);

        PersonalInfo info = resume.getPersonalInfo();
        if(info == null){
            info = new PersonalInfo();
        }
        if(personalInfo.getFirstName()!=null){
            info.setFirstName(personalInfo.getFirstName());
        }
        if(personalInfo.getLastName()!=null){
            info.setLastName(personalInfo.getLastName());
        }
        if(personalInfo.getEmail()!=null){
            info.setEmail(personalInfo.getEmail());
        }
        if(personalInfo.getPhone()!=null){
            info.setPhone(personalInfo.getPhone());
        }
        if(personalInfo.getPhone()!=null){
            info.setPhone(personalInfo.getPhone());
        }
        if(personalInfo.getHeadline()!=null){
            info.setHeadline(personalInfo.getHeadline());
        }
        if(personalInfo.getCity()!=null){
            info.setCity(personalInfo.getCity());
        }
        if(personalInfo.getCountry()!=null){
            info.setCountry(personalInfo.getCountry());
        }
        if(personalInfo.getLinkedinUrl()!=null){
            info.setLinkedinUrl(personalInfo.getLinkedinUrl());
        }
        if(personalInfo.getGithubUrl()!=null){
            info.setGithubUrl(personalInfo.getGithubUrl());
        }
        if(personalInfo.getPortfolioUrl()!=null){
            info.setPortfolioUrl(personalInfo.getPortfolioUrl());
        }
        if(personalInfo.getWebsiteUrl()!=null){
            info.setWebsiteUrl(personalInfo.getWebsiteUrl());
        }
        resume.setPersonalInfo(info);

        return buildFullResponse(resumeRepo.save(resume));
    }

    @Override
    public ResumeResponse updateSummary(Long resumeId, Long candidateId, String summary) throws Exception {
        Resume resume = getResumeEntityById(resumeId);
        asserOwner(resume, candidateId);
        resume.setSummary(summary);

        return buildFullResponse(resumeRepo.save(resume));
    }

    @Override
    public ResumeResponse setDefaultResume(Long resumeId, Long candidateId) throws Exception {
        Resume resume = getResumeEntityById(resumeId);
        asserOwner(resume, candidateId);
        resumeRepo.findByCandidateIdAndIsDefaultTrueAndIsActiveTrue(candidateId)
                .ifPresent(
                        existing -> {
                            existing.setIsDefault(false);
                            resumeRepo.save(existing);
                        }
                );
        resume.setIsDefault(true);
        return buildFullResponse(resumeRepo.save(resume));
    }

    @Override
    public void deleteResume(Long resumeId, Long candidateId) throws Exception {
        Resume resume = getResumeEntityById(resumeId);
        asserOwner(resume, candidateId);
        resume.setIsActive(false);
        resume.setIsDefault(false);
        buildFullResponse(resumeRepo.save(resume));

    }
    @Override
    public Resume getResumeEntityById(Long resumeId) throws Exception {
        return resumeRepo.findById(resumeId).orElseThrow(
                ()-> new Exception(" resume not found with id" +resumeId)
        );
    }

    private ResumeResponse buildFullResponse(Resume resume){
        return ResumeMapper.toResumeResponse(resume);
    }

    private void asserOwner(Resume resume, Long candidateId) throws Exception {
        if(!resume.getCandidateId().equals(candidateId)){
            throw new Exception("resume not found with the given id");
        }
    }
}

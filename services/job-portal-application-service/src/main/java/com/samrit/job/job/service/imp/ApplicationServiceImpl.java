package com.samrit.job.job.service.imp;

import com.samrit.job.domain.ApplicationStatus;
import com.samrit.job.dto.ApplicationResponse;
import com.samrit.job.dto.CompanyResponse;
import com.samrit.job.dto.JobResponse;
import com.samrit.job.job.mapper.ApplicationMapper;
import com.samrit.job.job.model.Application;
import com.samrit.job.job.payload.CompanyApplicationFilterRequest;
import com.samrit.job.job.payload.CreateApplicationRequest;
import com.samrit.job.job.payload.UpdateApplicationStatusRequest;
import com.samrit.job.job.payload.WithdrawApplicationRequest;
import com.samrit.job.job.repo.ApplicationRepository;
import com.samrit.job.job.repo.ApplicationSpecification;
import com.samrit.job.job.service.ApplicationService;
import com.samrit.job.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;

    @Override
    public ApplicationResponse createApplication(Long candidateId, CreateApplicationRequest request) throws Exception {
        if(applicationRepository.existByCandidateIdAndJobId(candidateId, request.getJobId())){
            throw new Exception("You have already applied");
        }
        // companyId  todo when fetch job use job.companyID
        Long companyId = 1L;
        Long employerId =1L;
        // todo: fetch job
        Long jobId =1L;

        // todo : fetch resume

        Application application = ApplicationMapper.toEntity(request,
                candidateId, companyId, employerId);

        //todo  ai screening runs in the background thread, no callback needed

        return buildFullResponse(applicationRepository.save(application));
    }

    @Override
    public ApplicationResponse getApplicationById(Long id) throws Exception {

        return buildFullResponse(getApplicationEntity(id));
    }

    @Override
    public List<ApplicationResponse> getMyApplication(Long candidateId) {
        return applicationRepository.findByCandidateId(candidateId)
                .stream().map(
                        this::buildFullResponse).toList();
    }

    @Override
    public List<ApplicationResponse> getApplicationForCompany(Long userId, CompanyApplicationFilterRequest filterRequest) {
//todo getting companyId using userId (ownerId) when implementing Feign
        Long companyId =1L;
        Sort sort = bulidSort (filterRequest.getSortBy());
        return applicationRepository.findAll(
                ApplicationSpecification.forCompanyWithFilters(
                        companyId,
                        filterRequest.getJobId(),
                        filterRequest.getStatus(),
                        filterRequest.getIsStarred(),
                        filterRequest.getAiShortListStatus(),
                        filterRequest.getMiniScore()
                ),
                sort)
                .stream()
                .map(
                        this::buildFullResponse
                ).toList();
    }

    @Override
    public List<ApplicationResponse> getApplicationForJob(Long jobId) {
        return applicationRepository.findByJobId(jobId)
                .stream().map(
                        this::buildFullResponse).toList();

    }
    @Override
    public ApplicationResponse updateStatus(Long applicationId, Long employerId, UpdateApplicationStatusRequest status) throws Exception {

        Application application = getApplicationEntity(applicationId);
        assertEmployer(application, employerId);
        if(application.getApplicationStatus()== ApplicationStatus.WITHDRAWN){
            throw new Exception("candidate have already withdrawn");
        }
        application.setApplicationStatus(status.getStatus());

        return buildFullResponse(applicationRepository.save(application));
    }
    @Override
    public ApplicationResponse withdraw(Long applicationId, Long candidateId, WithdrawApplicationRequest req) throws Exception {
        Application application = getApplicationEntity(applicationId);
        asserCandidate(application, candidateId);
        application.setApplicationStatus(ApplicationStatus.WITHDRAWN);
        application.setWithdrawnReason(req.getReason());
        return buildFullResponse(applicationRepository.save(application));
    }
    @Override
    public ApplicationResponse toggleStar(Long applicationId, Long employerId) throws Exception {
        Application application = getApplicationEntity(applicationId);
        assertEmployer(application, employerId);
        application.setIsStarred(!application.getIsStarred());
        return buildFullResponse(applicationRepository.save(application));
    }
    @Override
    public void deleteApplication(Long applicationId, Long candidateId) throws Exception {
        Application application = getApplicationEntity(applicationId);
        asserCandidate(application, candidateId);
        applicationRepository.delete(application);
    }
    @Override
    public Application getApplicationEntity(Long id) throws Exception {
        return  applicationRepository.findById(id).orElseThrow(
                ()-> new Exception("Application not found")
        );
    }
    private Sort bulidSort(String sortBy) {
        if("AI_SCORE_DESC".equals(sortBy)){
            return Sort.by(Sort.Order.desc("aiScore")
                    .with(Sort.NullHandling.NULLS_LAST));
        } else if ("AI_SCORE_ASC".equals(sortBy)) {
            return Sort.by(Sort.Order.asc("aiScore")
                    .with(Sort.NullHandling.NULLS_LAST));

        }
        return Sort.by(Sort.Direction.DESC, "appliedAt");
    }
    public ApplicationResponse buildFullResponse(Application application){

        // Todo fetch real data from respective microservice
        JobResponse jobResponse = JobResponse.builder().id(application.getId()).build();
        CompanyResponse company = CompanyResponse.builder().id(application.getCompanyId()).build();
        UserResponse candidate = UserResponse.builder().id(application.getCandidateId()).build();
        return  ApplicationMapper.toResponse(application, jobResponse, company, candidate);
    }
    private void assertEmployer(Application application, Long employerId) throws Exception {
        if(!application.getEmployerId().equals(employerId)){
            throw new Exception("you are not the employer for this application");
        }
    }
    private void asserCandidate(Application application, Long candidateId) throws Exception {
        if(!application.getCandidateId().equals(candidateId)){
            throw  new Exception("You are not the owner of this application");
        }
    }
}

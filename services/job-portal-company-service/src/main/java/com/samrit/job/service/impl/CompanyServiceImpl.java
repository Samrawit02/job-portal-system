package com.samrit.job.service.impl;

import com.samrit.job.Repo.CompanyRepo;
import com.samrit.job.domain.CompanyStatus;
import com.samrit.job.domain.CompanyType;
import com.samrit.job.domain.IndustryType;
import com.samrit.job.dto.CompanyRequest;
import com.samrit.job.dto.CompanyResponse;
import com.samrit.job.dto.SocialLinkResponse;
import com.samrit.job.mapper.CompanyMapper;
import com.samrit.job.model.Company;
import com.samrit.job.model.SocialLink;
import com.samrit.job.service.CompanyService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepo companyRepo;

    @Override
    public CompanyResponse createCompany(Long ownerId, CompanyRequest request) throws Exception {

        if(companyRepo.existsByOwnerId(ownerId)){
            throw  new Exception("You already have a company registred. " +
                    "Only one company per account is allowed.");
        }
        if(companyRepo.existsByName(request.getName())){
            throw new Exception("Company already exists. Please choose a different name.");
        }
        if(request.getRegistrationNumber() !=null  &&
        companyRepo.existsByRegistrationNumber(request.getRegistrationNumber())){
            throw new Exception("Company already exists. Please choose a different " +
                    "registration number");
        }
        String slug = generateUniqueSlug(request.getName());
        Company company = Company.builder()
                .name(request.getName())
                .slug(slug)
                .tagline(request.getTagline())
                .description(request.getDescription())
                .logoUrl(request.getLogoUrl())
                .coverImageUrl(request.getCoverImageUrl())
                .website(request.getWebsite())
                .email(request.getEmail())
                .phone(request.getPhone())
                .foundedYear(request.getFoundedYear())
                .companySize(request.getCompanySize())
                .companyType(request.getCompanyType())
                .industryType(request.getIndustryType())
                .registrationNumber(request.getRegistrationNumber())
                .ownerId(ownerId)
                .socialLinkList(mapSocialLinks(request.getSocialLinkList()))

                .build();
        return CompanyMapper.toCompanyResponse(companyRepo.save(company));
    }

    private List<SocialLink> mapSocialLinks(List<SocialLinkResponse> socialLinkList) {
        if(socialLinkList ==null || socialLinkList.isEmpty()){
            return new ArrayList<>();
        }
        return  socialLinkList.stream()
                .map(e-> SocialLink.builder()
                        .platform((e.getPlatform()))
                        .url(e.getUrl())
                        .build())
                .collect(toList());
    }

    private String generateUniqueSlug(String name) {
        String base = name.toLowerCase()
                .replaceAll("[^a-z0-9\\s-]", "").trim()
                .replaceAll("[\\s-]", "-");
        if(!companyRepo.existsBySlug(base)){
            return  base;
        }
        int counter = 1;
        while (companyRepo.existsBySlug(base+"-"+counter)){
            counter++;
        }
        return base +"-"+counter;
    }

    @Override
    public CompanyResponse getCompanyById(Long id) throws Exception {
        return CompanyMapper.toCompanyResponse(companyRepo.findById(id)
                .orElseThrow(()-> new Exception("Company not found")));
    }

    @Override
    public CompanyResponse getMyCompany(Long ownerId) throws Exception {
        return CompanyMapper.toCompanyResponse(
                companyRepo.findByOwnerId(ownerId)
                        .orElseThrow(()-> new Exception("No company found by the given owner id"))
        );
    }

    @Override
    public List<CompanyResponse> getAllCompanies(CompanyType companyType, IndustryType industryType, CompanyStatus companyStatus) {
        return companyRepo.findByFilters(
                companyType, industryType,companyStatus
                )
                .stream()
                .map(CompanyMapper::toCompanyResponse).collect(toList());
    }

    @Override
    public CompanyResponse updateCompany(Long companyId, Long ownerId, CompanyRequest req) throws Exception {
        Company existingCompany= companyRepo.findById(companyId)
                .orElseThrow(()-> new Exception("Company not found"));

        if(Objects.equals(ownerId, existingCompany.getOwnerId())){
           existingCompany.setName(req.getName()) ;
           existingCompany.setSlug(req.getSlug()) ;
           existingCompany.setTagline(req.getTagline());
           existingCompany.setDescription(req.getDescription());
           existingCompany.setLogoUrl(req.getLogoUrl());
           existingCompany.setCoverImageUrl(req.getCoverImageUrl());
           existingCompany.setWebsite(req.getWebsite());
           existingCompany.setEmail(req.getEmail());
           existingCompany.setPhone(req.getPhone());
           existingCompany.setFoundedYear(req.getFoundedYear());
           existingCompany.setCompanySize(req.getCompanySize());
           existingCompany.setCompanyType(req.getCompanyType());
           existingCompany.setIndustryType(req.getIndustryType());
           existingCompany.setRegistrationNumber(req.getRegistrationNumber());
           existingCompany.setOwnerId(ownerId);
           existingCompany.setSocialLinkList(mapSocialLinks(req.getSocialLinkList()));

         return  CompanyMapper.toCompanyResponse(companyRepo.save(existingCompany));
        }

        return null;
    }

    @Override
    public CompanyResponse verifyCompany(Long companyId) throws Exception {
        Company company = getCompanyEntityById(companyId);
        company.setStatus(CompanyStatus.ACTIVE);
        company.setVerified(true);

        return CompanyMapper.toCompanyResponse(companyRepo.save(company));
    }
    @Override
    public void deleteCompany(Long companyId, Long ownerId) throws Exception {
        Company company = getCompanyEntityById(companyId);
        if(!company.getOwnerId().equals(ownerId)){
            throw  new Exception("You don't have permission to delete this company");

        }
        companyRepo.delete(company);
    }
    @Override
    public Company getCompanyEntityById(Long id) throws Exception {
        return  companyRepo.findById(id).orElseThrow(
                ()-> new Exception("Company not found with id")
        );
    }
    @Override
    public CompanyResponse deactivateCompany(Long companyId) throws Exception {
        Company company = getCompanyEntityById(companyId);
        company.setStatus((CompanyStatus.SUSPENDED));
        company.setVerified(false);
        return CompanyMapper.toCompanyResponse(companyRepo.save(company));

    }
}

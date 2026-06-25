package com.samrit.job.service;

import com.samrit.job.domain.CompanyStatus;
import com.samrit.job.domain.CompanyType;
import com.samrit.job.domain.IndustryType;
import com.samrit.job.dto.CompanyRequest;
import com.samrit.job.dto.CompanyResponse;
import com.samrit.job.model.Company;

import java.util.List;

public interface CompanyService {

    CompanyResponse createCompany(Long ownerId, CompanyRequest request) throws Exception;
    CompanyResponse getCompanyById(Long id) throws Exception;
    CompanyResponse getMyCompany(Long ownerId) throws Exception;
    List<CompanyResponse> getAllCompanies(CompanyType companyType,
                                          IndustryType industryType,
                                          CompanyStatus companyStatus);
    CompanyResponse updateCompany(Long companyId, Long ownerId, CompanyRequest req) throws Exception;
    CompanyResponse verifyCompany(Long companyId) throws Exception;
    void deleteCompany(Long companyId , Long ownerId) throws Exception;
    Company getCompanyEntityById(Long id) throws Exception;

    CompanyResponse deactivateCompany(Long companyId) throws Exception;


}

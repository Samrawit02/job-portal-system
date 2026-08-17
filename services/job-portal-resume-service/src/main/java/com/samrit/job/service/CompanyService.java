package com.samrit.job.service;

import com.samrit.job.dto.CompanyResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "COMPANY-SERVICE")
public interface CompanyService {
    @GetMapping("/api/companies/my")
    public CompanyResponse getCompanyProfile(@RequestHeader ("X-User-Id") Long ownerId);

}

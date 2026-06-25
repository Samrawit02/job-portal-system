package com.samrit.job.controller;


import com.samrit.job.domain.CompanyStatus;
import com.samrit.job.domain.CompanyType;
import com.samrit.job.domain.IndustryType;
import com.samrit.job.dto.ApiMessage;
import com.samrit.job.dto.CompanyRequest;
import com.samrit.job.dto.CompanyResponse;
import com.samrit.job.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<CompanyResponse>createCompany(
            @RequestHeader ("X-User-Id") Long ownerId,
            @RequestBody @Valid CompanyRequest request
            ) throws Exception {

        return  ResponseEntity.status(HttpStatus.CREATED)
                .body(companyService.createCompany(ownerId, request));
    }
    @GetMapping("/{companyId}")
    public ResponseEntity<CompanyResponse>getCompanyById(
            @PathVariable Long companyId
    ) throws Exception {

        return  ResponseEntity.ok(companyService.getCompanyById(companyId));

    }
    @GetMapping("/my")
    public ResponseEntity<CompanyResponse>getCompanyByOwnerId(
            @RequestHeader ("X-User-Id") Long ownerId
    ) throws Exception {
        return  ResponseEntity.ok(companyService.getMyCompany(ownerId));

    }
    @GetMapping
    public ResponseEntity<List<CompanyResponse>>getAllCompanies(
            @RequestParam (required = false)CompanyType companyType,
            @RequestParam (required = false)IndustryType industryType,
            @RequestParam(required = false)CompanyStatus companyStatus
            ) throws Exception {
        return  ResponseEntity.ok(companyService.getAllCompanies(companyType, industryType,companyStatus));

    }
    @PutMapping("{id}")
    public ResponseEntity<CompanyResponse> updateCompany(
            @PathVariable Long id,
            @RequestHeader ("X-User-Id") Long ownerId,
            @RequestBody @Valid CompanyRequest companyRequest
    ) throws Exception {
        return   ResponseEntity.ok(companyService.updateCompany(id, ownerId, companyRequest));
    }
    @PatchMapping("/{id}/verify")
    public ResponseEntity<CompanyResponse> verifyCompany(
            @PathVariable Long id
    ) throws Exception {
        return  ResponseEntity.ok(companyService.verifyCompany(id));
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<CompanyResponse> deactivateCompany(
            @PathVariable Long id
    ) throws Exception {
        return  ResponseEntity.ok(companyService.deactivateCompany(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiMessage> deleteCompany(
            @PathVariable Long id,
            @RequestHeader ("X-User-Id") Long ownerId

    ) throws Exception {
        companyService.deleteCompany(id,ownerId);
        return  ResponseEntity.ok(
                new ApiMessage("Company deleted Successfully", true));
    }

}

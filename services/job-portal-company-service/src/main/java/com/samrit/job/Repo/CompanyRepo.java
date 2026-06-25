package com.samrit.job.Repo;

import com.samrit.job.domain.CompanyStatus;
import com.samrit.job.domain.CompanyType;
import com.samrit.job.domain.IndustryType;

import com.samrit.job.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CompanyRepo extends JpaRepository<Company, Long> {

    Optional<Company> findByOwnerId(Long ownerId);
    boolean existsByOwnerId(Long ownerId);
    boolean existsByName(String name);
    boolean existsBySlug(String slug);
    boolean existsByRegistrationNumber(String registrationNumber);

    @Query("select c from Company c where  " +
            "(:companyType Is NULL OR c.companyType=:companyType) AND " +
            "(:industryType Is NULL OR c.industryType=:industryType ) AND " +
            "(:status IS NULL OR c.status=:status)")
    List<Company> findByFilters(
            @Param("companyType")CompanyType companyType,
            @Param("industryType")IndustryType industryType,
            @Param("status")CompanyStatus status
            );

}

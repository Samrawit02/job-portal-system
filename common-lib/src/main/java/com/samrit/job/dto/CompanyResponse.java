package com.samrit.job.dto;

import com.samrit.job.domain.CompanySize;
import com.samrit.job.domain.CompanyStatus;
import com.samrit.job.domain.CompanyType;
import com.samrit.job.domain.IndustryType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CompanyResponse {
    private Long id;
    private String name;
    private String slug;
    private String tagline;
    private String description;
    private String logoUrl;
    private String coverImageUrl;
    private String website;
    private String email;
    private String phone;
    private Integer foundedYear;
    private CompanySize companySize;
    private CompanyType companyType;
    private IndustryType industryType;
    private CompanyStatus companyStatus;
    private String registrationNumber;
    private Long ownerId;
    private List<SocialLinkResponse> socialLinkList;
    private Boolean active= true;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}

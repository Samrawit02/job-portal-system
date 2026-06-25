package com.samrit.job.mapper;

import com.samrit.job.dto.CompanyResponse;
import com.samrit.job.dto.SocialLinkResponse;
import com.samrit.job.model.Company;
import com.samrit.job.model.SocialLink;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CompanyMapper {



    public static CompanyResponse toCompanyResponse(Company company){
        return CompanyResponse.builder()
                .id(company.getId())
                .name(company.getName())
                .slug(company.getSlug())
                .tagline(company.getTagline())
                .description(company.getDescription())
                .logoUrl(company.getLogoUrl())
                .coverImageUrl(company.getCoverImageUrl())
                .website(company.getWebsite())
                .email(company.getEmail())
                .phone(company.getPhone())
                .foundedYear(company.getFoundedYear())
                .companySize(company.getCompanySize())
                .companyType(company.getCompanyType())
                .companyStatus(company.getStatus())
                .industryType(company.getIndustryType())
                .active(company.getActive())
                .ownerId(company.getOwnerId())
                .socialLinkList(company.getSocialLinkList()
                        .stream()
                        .map(CompanyMapper::toSocialLinkResponse).collect(Collectors.toList()))
                .createdAt(company.getCreatedAt())
                .updatedAt(company.getUpdatedAt())
                .build();
    }

    public static SocialLinkResponse toSocialLinkResponse(SocialLink socialLink) {

        if(socialLink ==null) return  null;

        return  SocialLinkResponse.builder()
                .platform(socialLink.getPlatform())
                .url(socialLink.getUrl())
                .build();
    }
}

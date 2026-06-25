package com.samrit.job.dto;

import com.samrit.job.domain.CompanySize;
import com.samrit.job.domain.CompanyStatus;
import com.samrit.job.domain.CompanyType;
import com.samrit.job.domain.IndustryType;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CompanyRequest {

    @NotBlank(message = "company name is required")
    private String name;
    private String slug;
    private String tagline;
    private String logoUrl;
    private String description;
    private String coverImageUrl;
    @Email(message = "Company email must be valid")
    private String email;
    private String phone;
    @Pattern(regexp = "^(https?://).*", message = "Website must be a valid URL")
    private String website;
    @Min( value = 1800 , message = "Founder year seems too old")
    @Max(value = 2100,message = "Founder year is invalid")
    private Integer foundedYear;
    @NotNull(message = "Company size is required")
    private CompanySize companySize;
    @NotNull(message = "Company type is required")
    private CompanyType companyType;
    @NotNull(message = "Industry type is required")
    private IndustryType industryType;
    private String registrationNumber;
    private List<SocialLinkResponse> socialLinkList;

}

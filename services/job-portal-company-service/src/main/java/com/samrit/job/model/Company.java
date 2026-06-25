package com.samrit.job.model;

import com.samrit.job.domain.CompanySize;
import com.samrit.job.domain.CompanyStatus;
import com.samrit.job.domain.CompanyType;
import com.samrit.job.domain.IndustryType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(unique = true)
    private String slug;
    private String tagline;
    private String description;
    private String logoUrl;
    private String coverImageUrl;
    private String website;
    private String email;
    private String phone;
    private Integer foundedYear;
    @Enumerated(EnumType.STRING)
    private CompanySize companySize;
    @Enumerated(EnumType.STRING)
    private CompanyType companyType;
    @Enumerated(EnumType.STRING)
    private IndustryType industryType;
    private CompanyStatus status;

    private  boolean isVerified=false;
    @Column(unique = true)
    private String registrationNumber;
    @Column(nullable = false, unique = true)
    private Long ownerId;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<SocialLink> socialLinkList;

    private Boolean active= true;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}

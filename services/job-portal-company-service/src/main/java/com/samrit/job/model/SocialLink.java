package com.samrit.job.model;

import com.samrit.job.domain.SocialPlatform;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Enumerated;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SocialLink {
    private SocialPlatform platform;
    private String  url;
}

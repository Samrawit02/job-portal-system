package com.samrit.job.dto;

import com.samrit.job.domain.SocialPlatform;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SocialLinkResponse {
    private SocialPlatform platform;
    private String url;

}

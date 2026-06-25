package com.samrit.job.payload;

import lombok.Data;

@Data
public class UpdatedUserRequest {
    private String fullName;
    private String phone;
    private String profileImage;

}

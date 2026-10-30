package com.samrit.job.job.payload;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class AddApplicationRequest {
    @NotBlank(message = "Note content is required")
    @Size(max = 2000, message = "Note must not exceed 2000 characters")
    private String content;



}

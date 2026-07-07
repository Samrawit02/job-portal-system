package com.samrit.job.payload;


import com.samrit.job.model.JobCategory;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobCategoryRequest {

    @NotNull(message = "Description must not exceed 500 characters")
    private String name;

    @NotNull(message = "Description must not exceed 500 characters")
    private String description;

    private  String iconUrl;
    private Long parentId;
    private List<JobCategory> subCategories = new ArrayList<>();
    private Boolean active= true;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
}

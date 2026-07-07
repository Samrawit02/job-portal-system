package com.samrit.job.mapper;

import com.samrit.job.dto.JobResponse;
import com.samrit.job.model.JobCategory;

import java.util.List;
import java.util.stream.Collectors;


public class JobCategoryMapper {


    public static JobResponse.JobCategoryResponse toJobCategoryResponse(JobCategory category ,
                                                                        boolean includeChildren) {
        List<JobResponse.JobCategoryResponse> subCategories = null;
        if(includeChildren && category.getSubCategories() !=null ){
            subCategories = category.getSubCategories()
                    .stream()
                    .map(sub-> toJobCategoryResponse(sub, false))
                    .collect(Collectors.toList());
        }

        return JobResponse.JobCategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .slug(category.getSlug())
                .description(category.getDescription())
                .iconUrl(category.getIconUrl())
                .active(category.getActive())
                .parentId(category.getParent() != null ? category.getParent().getId() : null)
                .parentName(category.getParent() != null ? category.getParent().getName() : null)
                .subCategories(subCategories)
                .createAt(category.getCreatedAt())
                .build();
    }

}

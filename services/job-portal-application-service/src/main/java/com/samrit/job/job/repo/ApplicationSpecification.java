package com.samrit.job.job.repo;

import com.samrit.job.domain.AiShortListStatus;
import com.samrit.job.domain.ApplicationStatus;
import com.samrit.job.job.model.Application;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;


public class ApplicationSpecification {
    public static Specification<Application> forCompanyWithFilters(
            Long companId,
            Long jobId,
            ApplicationStatus status,
            boolean isStarred,
            AiShortListStatus aiShortListStatus,
            Integer minAiScore

    ){
        return (root, query, cb)->{
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("companyId"), companId));
            if(jobId !=null) predicates.add(cb.equal(root.get("jobId"), jobId));
            if(status !=null) predicates.add(cb.equal(root.get("applicationStatus"), status));
            if(isStarred) predicates.add(cb.equal(root.get("isStarred"), isStarred));
            if(aiShortListStatus !=null) predicates.add(cb.equal(
                    root.get("aiShortListStatus"), aiShortListStatus
            ));
            if(minAiScore !=null) predicates.add(cb.greaterThanOrEqualTo(
                    root.get("aiScore"), minAiScore
            ));

            return cb.and((predicates.toArray(new Predicate[0])));


        };
    }
}

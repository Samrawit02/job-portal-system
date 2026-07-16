package com.samrit.job.model;


import com.samrit.job.domain.ProficiencyLevel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "resume_skills")
public class ResumeSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Resume resume;
    @Column(nullable = false)
    private String skillName;

    private ProficiencyLevel proficiencyLevel = ProficiencyLevel.BEGINNER;

    private Integer yearsOfExperience;
    @Column(nullable = false)
    private Integer displayOrder =0;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

}

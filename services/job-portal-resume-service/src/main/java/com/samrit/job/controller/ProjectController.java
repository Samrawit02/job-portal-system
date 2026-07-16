package com.samrit.job.controller;

import com.samrit.job.dto.ApiMessage;
import com.samrit.job.dto.ProjectResponse;
import com.samrit.job.payload.ProjectRequest;
import com.samrit.job.service.ProjectService;
import com.samrit.job.service.ResumeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/resumes/{resumeId}/projects")
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<ProjectResponse> addProject(
            @PathVariable Long resumeId,
            @RequestHeader("X-User-Id") Long candidateId,
            @RequestBody @Valid ProjectRequest request
            ) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(projectService.addProject(resumeId,candidateId,request));
    }

    @GetMapping
    public ResponseEntity<List<ProjectResponse>> getProjects(
            @PathVariable Long resumeId
    ){
        return ResponseEntity.ok(projectService.getAllProjects(resumeId));
    }
    @PutMapping("/{projectId}")
    public ResponseEntity<ProjectResponse>  updateProject (
            @PathVariable Long projectId,
            @PathVariable Long resumeId,
            @RequestHeader("X-User-Id") Long candidateId,
            @RequestBody @Valid ProjectRequest request
    ) throws Exception {
        return ResponseEntity.ok(projectService.updateProject(projectId,resumeId,candidateId, request));
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<ApiMessage>  deleteProject (
            @PathVariable Long projectId,
            @PathVariable Long resumeId,
            @RequestHeader("X-User-Id") Long candidateId

    ) throws Exception {
        projectService.deleteProject(projectId,resumeId,candidateId);
        return ResponseEntity.ok(new ApiMessage("Project is deleted successfully", true));
    }


}

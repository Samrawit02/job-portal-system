package com.samrit.job.controller;


import com.samrit.job.domain.UserRole;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping
    public String HomeController(){
        return "Job Portal System ----  "+ UserRole.ROLE_JOB_SEEKER;
    }
}

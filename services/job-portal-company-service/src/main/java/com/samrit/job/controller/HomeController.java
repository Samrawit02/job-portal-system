package com.samrit.job.controller;

import com.samrit.job.domain.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HomeController {

    @GetMapping
    public  String home(){
        return "Service for managing company profile, and documents " + UserRole.ROLE_EMPLOYER;
    }

}

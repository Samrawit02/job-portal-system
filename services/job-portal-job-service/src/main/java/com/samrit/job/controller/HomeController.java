package com.samrit.job.controller;


import com.samrit.job.domain.UserRole;
import com.samrit.job.dto.ApiMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HomeController {

    @GetMapping
    public ApiMessage HomController(){
        return
                new ApiMessage("Service for managing job postings, search, and filtering" +
                        UserRole.ROLE_EMPLOYER,true);
    }

}

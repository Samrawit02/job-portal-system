package com.samrit.job.controller;


import com.samrit.job.dto.ApiMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class HomeController {
    @GetMapping
    public ResponseEntity<ApiMessage> HomeController(){
        ApiMessage apiMessage = new ApiMessage();
        apiMessage.setMessage("Service for managing candidates resumes, including builder, \n" +
                "\t\tmultiple versions, and resume parsing");
        apiMessage.setStatus(true);
        return ResponseEntity.ok(apiMessage);
    }
}

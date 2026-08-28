package com.samrit.job.service;

import com.samrit.job.response.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER-SERVICE", url = "localhost:5001")
public interface UserService {
    @GetMapping("/api/users/{userId}")
   UserResponse getUserById(@PathVariable Long userId);

}

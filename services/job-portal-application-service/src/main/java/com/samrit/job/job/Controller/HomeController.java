
package com.samrit.job.job.Controller;

import com.samrit.job.dto.ApiMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public ResponseEntity<?> home(){
        String message =  "Service for managing job applications," +
                " tracking application status, " +
                " and maintaining application history";

        ApiMessage apiMessage = new ApiMessage(message, true);
        return  ResponseEntity.ok(apiMessage);

    }
}

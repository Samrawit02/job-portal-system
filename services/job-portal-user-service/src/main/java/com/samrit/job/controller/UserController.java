package com.samrit.job.controller;



import com.samrit.job.mapper.UserMapper;
import com.samrit.job.model.User;
import com.samrit.job.payload.UpdatedUserRequest;
import com.samrit.job.response.UserResponse;
import com.samrit.job.service.UserService;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.Update;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<UserResponse> gerProfile(@RequestHeader("X-User-Email") String email) throws Exception {
        return ResponseEntity.ok(UserMapper.toDTO(userService.getUserByEmail(email)));
    }
    @PutMapping("/profile")
    public ResponseEntity<UserResponse>updateProfile(
            @RequestHeader("X-User-Email")String email,
            @RequestBody UpdatedUserRequest request) throws Exception {
        return ResponseEntity.ok(userService.updateProfile(email, request));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) throws Exception {
        return ResponseEntity.ok(userService.getUserById(userId));
    }
    @GetMapping("/")
    public ResponseEntity<List<UserResponse>> getAllUser() throws Exception {
        return ResponseEntity.ok(userService.getAllUsers()
                .stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList()));
    }
    @PatchMapping("/{userId}/activate")
    public ResponseEntity<UserResponse> activateUser(
            @PathVariable Long userId
    ) throws Exception {
        return ResponseEntity.ok(userService.activateUser(userId));
    }
    @PatchMapping("/{userId}/suspend")
    public ResponseEntity<UserResponse> suspendUser(
            @PathVariable Long userId
    ) throws Exception {
        return ResponseEntity.ok(userService.suspendUser(userId));
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<UserResponse> deleteUser(
            @PathVariable Long userId
    ) throws Exception {
        return ResponseEntity.ok(userService.deleteUser(userId));
    }

}

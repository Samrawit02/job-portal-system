package com.samrit.job.service;

import com.samrit.job.model.User;
import com.samrit.job.payload.UpdatedUserRequest;
import com.samrit.job.response.UserResponse;


import java.util.List;

public interface UserService {
    User getUserByEmail (String email) throws Exception;
    User getUserById(Long id) throws Exception;
    List<User> getAllUsers();

    UserResponse updateProfile(String email, UpdatedUserRequest request) throws Exception;

    //admin action
    UserResponse suspendUser(Long id) throws Exception;
    UserResponse activateUser(Long id) throws Exception;
    UserResponse deleteUser(Long id) throws Exception;

}

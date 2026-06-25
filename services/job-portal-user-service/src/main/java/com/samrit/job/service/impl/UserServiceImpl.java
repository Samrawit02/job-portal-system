package com.samrit.job.service.impl;

import com.samrit.job.domain.UserStatus;
import com.samrit.job.mapper.UserMapper;
import com.samrit.job.model.User;
import com.samrit.job.payload.UpdatedUserRequest;
import com.samrit.job.repo.UserRepository;
import com.samrit.job.response.UserResponse;
import com.samrit.job.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;



    @Override
    public User getUserByEmail(String email) throws Exception {
        return userRepository.findByEmail(email).orElseThrow(
                ()-> new Exception("No user is found by this " +email)
        );
    }

    @Override
    public User getUserById(Long id) throws Exception {
        return userRepository.findById(id).orElseThrow(
                ()-> new Exception( " User not found")
        );
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserResponse updateProfile(String email, UpdatedUserRequest request) throws Exception {

        User existingUser = userRepository.findByEmail(email).orElseThrow(
                ()-> new Exception("User not found")
        );
        existingUser.setFullName(request.getFullName());
        existingUser.setPhone(request.getPhone());
        existingUser.setProfileImage(request.getProfileImage());
        return UserMapper.toDTO( userRepository.save(existingUser));
    }

    @Override
    public UserResponse suspendUser(Long id) throws Exception {

        User user = userRepository.findById(id).orElseThrow(
                ()-> new Exception("user not found")
        );
        user.setStatus( UserStatus.SUSPENDED);
        user.setSuspendedAt(LocalDateTime.now());

        return UserMapper.toDTO(userRepository.save(user));
    }

    @Override
    public UserResponse activateUser(Long id) throws Exception {

        User user = userRepository.findById(id).orElseThrow(
                ()-> new Exception("user not found")
        );
        user.setStatus( UserStatus.ACTIVE);
        user.setSuspendedAt(LocalDateTime.now());

        return UserMapper.toDTO(userRepository.save(user));
    }

    @Override
    public UserResponse deleteUser(Long id) throws Exception {
        User user = userRepository.findById(id).orElseThrow(
                ()-> new Exception("user not found")
        );
        user.setStatus(UserStatus.DELETED);
        user.setDeletedAt(LocalDateTime.now());
        return  UserMapper.toDTO(userRepository.save(user));
    }
}

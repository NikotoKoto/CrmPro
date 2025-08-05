package com.example.crm.User.service;

import com.example.crm.User.dto.UserResponseDto;
import com.example.crm.User.entity.User;
import com.example.crm.User.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService (UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public UserResponseDto getMe(String email){
        User user = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return new UserResponseDto(user.getId(), user.getEmail(), user.getFirstname());
    }
}

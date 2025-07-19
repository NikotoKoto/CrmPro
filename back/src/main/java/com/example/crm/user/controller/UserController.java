package com.example.crm.User.controller;

import com.example.crm.User.dto.UserResponseDto;
import com.example.crm.User.entity.User;
import com.example.crm.User.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> getMe(
            @AuthenticationPrincipal UserDetails userDetails){
        UserResponseDto dto = userService.getMe(userDetails.getUsername());
        return ResponseEntity.ok(dto);
    }

}

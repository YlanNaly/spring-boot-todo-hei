package com.example.springsecuritydemo.service;


import com.example.springsecuritydemo.model.Users;
import com.example.springsecuritydemo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final UserService userService;
    public String register(Users request) {

        return  userService.signUp(request);
    }
}

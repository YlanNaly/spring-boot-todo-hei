package com.example.springsecuritydemo.controller;

import com.example.springsecuritydemo.model.User;
import com.example.springsecuritydemo.service.RegistrationService;
import com.example.springsecuritydemo.model.Users;
import com.example.springsecuritydemo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin
public class UserController {
    private UserService userService;
    private RegistrationService RegistrationService;
    @GetMapping("/")
    public String hello(){
        return "Hello world";
    }

    @GetMapping("/users")
    public List<User> getUsers(){
        return userService.getAll();
    }
    @PostMapping(path="/authorization")
    public String register(@RequestBody Users request) {
        return RegistrationService.register(request);
    }

    @GetMapping("/users/{userId}")
    public User getUserById(@PathVariable Long userId){
        return userService.getById(userId);
    }



    @PostMapping("/users")
    public List<User> saveUsers(@RequestBody List<User> users){
        return userService.saveAll(users);
    }
}

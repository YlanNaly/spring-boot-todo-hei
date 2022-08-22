package com.example.springsecuritydemo.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SecurityService implements UserDetailsService {
    private final com.example.springsecuritydemo.repository.SecurityRepository SecurityRepository;
    private final static String User_error = "user with email %s not found";

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        return SecurityRepository.findByFirstName(name)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(User_error, name)));

    }

}
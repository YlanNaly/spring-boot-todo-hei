package com.example.springsecuritydemo.security;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SecurityService implements UserDetailsService {
    private final SecurityRepository SecurityRepository;
    private final static String User_error = "user with email %s not found";

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return SecurityRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(User_error, email)));

    }

    public String getUsername(String email){
        return SecurityRepository.getByEmail(email).getUsername();
    }
    public String getPassword(String email){
        return SecurityRepository.getByEmail(email).getPassword();
    }
    public String getRoles(String email) {
        return SecurityRepository.getByEmail(email).getRoles().toString();
    }
}
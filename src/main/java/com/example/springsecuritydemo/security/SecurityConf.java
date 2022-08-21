package com.example.springsecuritydemo.security;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@Slf4j
public class SecurityConf extends WebSecurityConfigurerAdapter {

    private final SecurityService securityService;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.GET , "/users/**").authenticated()
                .antMatchers(HttpMethod.GET , "/posts/**").permitAll()
                .and()
                .formLogin()
                .and()
                .logout().permitAll()
                .and()
                .csrf()
                .disable()
                .httpBasic();

    }
    @Override
    protected UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username(securityService.getUsername("hei.nalisoa@gmail.com"))
                .password(passwordEncoder().encode(securityService.getPassword("hei.nalisoa@gmail.com")))
                .roles(securityService.getRoles("hei.nalisoa@gmail.com"))
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}

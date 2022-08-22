package com.example.springsecuritydemo.service;

import com.example.springsecuritydemo.model.User;
import com.example.springsecuritydemo.repository.UserRepository;
import com.example.springsecuritydemo.repository.SecurityRepository;
import com.example.springsecuritydemo.model.Users;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final SecurityRepository repository;

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public User getById(Long userId){
        return userRepository.getReferenceById(userId);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }
    public String signUp(Users appUser) {
        boolean exist = repository.findByFirstName(appUser.getFirstName())
                .isPresent();
        if (exist) {
            throw new IllegalStateException("name already taken please add number");
        }
        System.out.println(appUser.getPassword());
        String encryptpassword = bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encryptpassword);
        repository.save(appUser);
        return "encryptpassword";
    }

    @Transactional
    public List<User> saveAll(List<User> users){
        return userRepository.saveAll(users);
    }
}

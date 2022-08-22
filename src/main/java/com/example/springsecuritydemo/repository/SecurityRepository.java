package com.example.springsecuritydemo.repository;

import com.example.springsecuritydemo.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SecurityRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByFirstName(String email);
    Users getByEmail(String name);
}

package com.denizbyrk.seafBank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.denizbyrk.seafBank.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
    Optional<User> findByUsername(String username);
    
    Optional<User> findByEmail(String email);
    
    Boolean existsByUsername(String username);
    
    Boolean existsByEmail(String email);
}
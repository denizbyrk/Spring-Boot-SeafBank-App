package com.denizbyrk.seafBank.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.denizbyrk.seafBank.model.Role;
import com.denizbyrk.seafBank.model.User;
import com.denizbyrk.seafBank.repository.UserRepository;
import com.denizbyrk.seafBank.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    	
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(String username, String email, String password) {
    	
        if (this.userRepository.existsByUsername(username)) {
            throw new RuntimeException("Username already taken");
        }
        
        if (this.userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email already in use");
        }

        User user = new User();
        
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(this.passwordEncoder.encode(password));
        user.setRole(Role.USER);

        return this.userRepository.save(user);
    }

    public Optional<User> getUserById(Long id) {
    	
        return this.userRepository.findById(id);
    }

    public List<User> getAllUsers() {
    	
        return this.userRepository.findAll();
    }
}
package com.denizbyrk.seafBank.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.denizbyrk.seafBank.DTO.RegisterRequestDTO;
import com.denizbyrk.seafBank.DTO.UserDTO;
import com.denizbyrk.seafBank.model.User;
import com.denizbyrk.seafBank.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    
    public UserController(UserService userService) {
    	
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody RegisterRequestDTO request) {
    	
        User user = userService.registerUser(request.getUsername(), request.getEmail(), request.getPassword());
        
        return ResponseEntity.ok(UserDTO.fromEntity(user));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
    	
        List<UserDTO> users = userService.getAllUsers()
                .stream()
                .map(UserDTO::fromEntity)
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(users);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
    	
        return userService.getUserById(id)
                .map(UserDTO::fromEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
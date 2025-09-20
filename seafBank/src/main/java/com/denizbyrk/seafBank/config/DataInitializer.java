package com.denizbyrk.seafBank.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.denizbyrk.seafBank.model.Role;
import com.denizbyrk.seafBank.model.User;
import com.denizbyrk.seafBank.repository.UserRepository;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initAdmin(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    	
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
            	
                User admin = new User();
                admin.setUsername("admin");
                admin.setEmail("admin@xxx.com");
                admin.setPassword(passwordEncoder.encode("1234"));
                admin.setRole(Role.ADMIN);

                userRepository.save(admin);
                System.out.println("Admin user created: username = admin, email = admin@xxx.com, password = 1234");
                
            } else {
            	
                System.out.println("Admin user already exists, skipping creation.\nusername = admin, email = admin@xxx.com, password = 1234\"");
            }
        };
    }
}
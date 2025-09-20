package com.denizbyrk.seafBank.DTO;

import com.denizbyrk.seafBank.model.Role;
import com.denizbyrk.seafBank.model.User;

public class UserDTO {
	
    private Long id;
    private String username;
    private String email;
    private Role role;

    public UserDTO(Long id, String username, String email, Role role) {
    	
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
    }

    public static UserDTO fromEntity(User user) {
    	
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole()
        );
    }

    public Long getId() {
    	
    	return id;
    }
    
    public String getUsername() {
    	
    	return username;
    }
    
    public String getEmail() {
    	
    	return email;
    }
    
    public Role getRole() {
    	
    	return role;
    }
}
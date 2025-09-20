package com.denizbyrk.seafBank.DTO;

public class JwtResponseDTO {

    private String token;
    
    public JwtResponseDTO(String token) {
    	
    	this.token = token;
    }
    
    public String getToken() {
    	
    	return token;
    }
}
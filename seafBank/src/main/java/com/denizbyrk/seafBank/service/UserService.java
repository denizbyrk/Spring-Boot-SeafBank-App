package com.denizbyrk.seafBank.service;

import java.util.List;
import java.util.Optional;

import com.denizbyrk.seafBank.model.User;

public interface UserService {

	public User registerUser(String username, String email, String password);
	
	public Optional<User> getUserById(Long id);
	
	public List<User> getAllUsers();
}
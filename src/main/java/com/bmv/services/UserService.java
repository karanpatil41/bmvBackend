package com.bmv.services;

import java.util.List;
import java.util.Map;

import com.bmv.entities.User;

public interface UserService {
	User createUser(User user);
	
	List<User> getAllUsers();
	
	User getUserByUsername(String username);
	
	User updateUser(String username, Map<String, Object> updates);
}

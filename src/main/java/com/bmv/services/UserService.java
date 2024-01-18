package com.bmv.services;

import java.util.List;

import com.bmv.entities.User;

public interface UserService {
	User createUser(User user);
	
	List<User> getAllUsers();
	
}

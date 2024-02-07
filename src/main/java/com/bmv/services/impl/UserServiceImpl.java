package com.bmv.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bmv.entities.User;
import com.bmv.repositories.UserRepo;
import com.bmv.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	private List<User> userList = new ArrayList<>();	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public User createUser(User user) {
		System.out.println("In User service");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User userAdded = this.userRepo.save(user);
		return userAdded;
	}

	@Override
	public List<User> getAllUsers() {
		List<User> usersList = userRepo.findAll();
		return usersList;
	}

}

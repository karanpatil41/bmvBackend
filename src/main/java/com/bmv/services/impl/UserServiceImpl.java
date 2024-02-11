package com.bmv.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bmv.entities.User;
import com.bmv.exception.ResourceNotFoundException;
import com.bmv.repositories.UserRepo;
import com.bmv.services.UserService;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	private List<User> userList = new ArrayList<>();	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private Logger logger = LoggerFactory.getLogger(OncePerRequestFilter.class);
	
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

	@Override
	@Transactional
	public User getUserByUsername(String username) {
		//calling stored procedure to retrieved specific user
		logger.info("UserServiceImpl's getUserByUsername");
		User user =userRepo.getUserByUsername(username);
		logger.info(" user=",user);
		logger.info("UserServiceImpl's getUserByUsername- user.toString()=",user.toString());
		return user; 
	}

	@Override
	public User updateUser(String username, Map<String, Object> updates) {
		User existingUser =userRepo.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("User not found with username "+username));
		
		logger.info("UserServiceImpl updateUser() existingUser=",existingUser);
		
		//update user fields dynamically
		updates.forEach((key, value) -> {
			switch (key) {
			case "firstName":
				existingUser.setFirstName((String) value);
				break;
			case "lastName":
				existingUser.setLastName((String) value);
				break;
			case "address":
				existingUser.setAddress((String) value);
				break;
			case "contactNumber":
				existingUser.setContactNumber((String) value);
				break;
			
			default:
				throw new IllegalArgumentException("Invalid field: " + key);
			}
		});
		return userRepo.save(existingUser);
	}
}

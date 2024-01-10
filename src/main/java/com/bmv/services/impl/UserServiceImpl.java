package com.bmv.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bmv.entities.User;
import com.bmv.repositories.UserRepo;
import com.bmv.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public User saveUser(User user) {
		System.out.println("In User service");
		User userAdded = this.userRepo.save(user);
		
		return userAdded;
	}

}

package com.bmv.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bmv.entities.User;
import com.bmv.repositories.UserRepo;


import jakarta.transaction.Transactional;

@Service
public class CustomUserDetailService implements UserDetailsService{

//	@Autowired
//	private UserRepository userRepository;
	
	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// load user from database
		System.out.println("In CustomUserDetailService's loadUserByUsername 1..."+username);
		User user = userRepo.findByEmail(username).orElseThrow(()-> new RuntimeException("User not found!!"));
		System.out.println("In CustomUserDetailService's loadUserByUsername-2..."+user.toString());
		return user;
	}

}

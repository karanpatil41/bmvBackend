package com.bmv.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bmv.entities.User;
import com.bmv.repositories.UserRepo;


import jakarta.transaction.Transactional;

@Service
public class CustomUserDetailService implements UserDetailsService{

//	@Autowired
//	private UserRepository userRepository;
	
	private Logger logger = LoggerFactory.getLogger(OncePerRequestFilter.class);
	
	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// load user from database
		
		logger.info("AUTHENTICATION-3-7-In CustomUserDetailService's loadUserByUsername()."+username);
		User user = userRepo.findByEmail(username).orElseThrow(()-> new RuntimeException("User not found!!"));
		logger.info("AUTHENTICATION-4-8-In CustomUserDetailService's loadUserByUsername()."+user.toString());
//		User us= new User(user);
		return user;
	}

}

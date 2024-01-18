package com.bmv.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bmv.entities.Usee;
import com.bmv.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	public List<Usee> getUsers() {
		return userRepository.findAll();
	}
	
	public Usee createUsee(Usee usee) {
		usee.setPassword(passwordEncoder.encode(usee.getPassword()));
		return userRepository.save(usee);
	}
}

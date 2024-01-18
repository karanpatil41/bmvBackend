package com.bmv.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bmv.entities.Usee;
import com.bmv.services.impl.UserService;

@RestController
@RequestMapping("/home")
public class HomeController {
	
	@Autowired
	private UserService userService;	
	
	//http://localhost:8080/home/user
	@GetMapping("/user")
	public List<Usee> getUser() {
		System.out.println("getting users");
		return this.userService.getUsers();
	}
	
	@GetMapping("/current-user")
	public String getLoggedInUser(Principal principal) {
		return principal.getName();
	}
}

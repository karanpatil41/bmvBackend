package com.bmv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bmv.entities.Usee;
import com.bmv.entities.User;
import com.bmv.model.JwtRequest;
import com.bmv.model.JwtResponse;
import com.bmv.security.JwtHelper;
import com.bmv.services.UserService;
//import com.bmv.services.impl.UserService;


@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private JwtHelper helper;
	 
//	@Autowired
//	private UserService userService;
	
	@Autowired
	private UserService userService;
	
	 @PostMapping("/login")
	    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

	        this.doAuthenticate(request.getEmail(), request.getPassword());

	        System.out.println("In Auth Controller's login method 1");
	        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
	        System.out.println("In Auth Controller's login method 2. userDetails: "+userDetails);
	        String token = this.helper.generateToken(userDetails);
	        System.out.println("In Auth Controller's login method 3. token: "+token);
	        
	        JwtResponse response = JwtResponse.builder()
	                .jwtToken(token)
	                .username(userDetails.getUsername()).build();
	        System.out.println("In Auth Controller's login method 4. response: "+response);
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    }

	    private void doAuthenticate(String email, String password) {
	    	System.out.println("In Auth Controller's doAuthenticate method 1. email: "+email+" password: "+password);
	        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
	        try {
	        	System.out.println("In Auth Controller's doAuthenticate method 2. authentication: "+authentication);
	            manager.authenticate(authentication);
	            System.out.println("In Auth Controller's doAuthenticate method 3. authentication: "+authentication);

	        } catch (BadCredentialsException e) {
	            throw new BadCredentialsException(" Invalid Username or Password  !!");
	        }

	    }

	    @ExceptionHandler(BadCredentialsException.class)
	    public String exceptionHandler() {
	        return "Credentials Invalid !!";
	    }
	    
//	    @PostMapping("/create-user")
//		public Usee createUser(@RequestBody Usee usee) {
//			return userService.createUsee(usee);
//		}
	    @PostMapping("/create-user")
		public User createUser(@RequestBody User user) {
			return userService.createUser(user);
		}
}

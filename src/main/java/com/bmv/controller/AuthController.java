package com.bmv.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bmv.entities.User;
import com.bmv.model.JwtRequest;
import com.bmv.model.JwtResponse;
import com.bmv.security.JwtHelper;
import com.bmv.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
//	@Autowired
//	private UserDetailsService userDetailsService;
//	
//	@Autowired
//	private AuthenticationManager manager;
//	
//	@Autowired
//	private JwtHelper helper;
//	 
////	@Autowired
////	private UserService userService;
//	
//	private Logger logger = LoggerFactory.getLogger(OncePerRequestFilter.class);
//	
//	@Autowired
//	private UserService userService;
//	
//	 @PostMapping("/login")
//	    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
//
//	        this.doAuthenticate(request.getEmail(), request.getPassword());
//
//	        logger.info("AUTHENTICATION-6-In Auth Controller's login method 1");
//	        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
//	        logger.info("AUTHENTICATION-9-In Auth Controller's login method 2. userDetails: "+userDetails);
//	        String token = this.helper.generateToken(userDetails);
//	        logger.info("AUTHENTICATION-13-In Auth Controller's login method 3. token: "+token);
//	        
//	        JwtResponse response = JwtResponse.builder()
//	                .jwtToken(token)
//	                .username(userDetails.getUsername()).build();
//	        logger.info("AUTHENTICATION-14-In Auth Controller's login method 4. response: "+response);
//
//	        return new ResponseEntity<>(response, HttpStatus.OK);
//	    }
//
//	    private void doAuthenticate(String email, String password) {
//	    	logger.info("AUTHENTICATION-1-In Auth Controller's doAuthenticate method 1. email: "+email+" password: "+password);
//	        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
//	        try {
//	        	logger.info("AUTHENTICATION-2-In Auth Controller's doAuthenticate method 2. authentication: "+authentication);
//	            manager.authenticate(authentication);
//	            logger.info("AUTHENTICATION-5-In Auth Controller's doAuthenticate method 3. authentication: "+authentication);
//
//	        } catch (BadCredentialsException e) {
//	        	logger.info("AUTHENTICATION--In Auth Controller's doAuthenticate method");
//	            throw new BadCredentialsException(" Invalid Username or Password  !!");
//	        }
//
//	    }
//
//	    @ExceptionHandler(BadCredentialsException.class)
//	    public String exceptionHandler() {
//	        return "Credentials Invalid !!";
//	    }
//	    
////	    @PostMapping("/create-user")
////		public Usee createUser(@RequestBody Usee usee) {
////			return userService.createUsee(usee);
////		}
//	    @PostMapping("/create-user")
//		public User createUser(@RequestBody User user) {
//			return userService.createUser(user);
//		}
//	    
//	    @GetMapping("/current-user")
//		public String getLoggedInUser(Principal principal) {
//			return principal.getName();
//		}
}

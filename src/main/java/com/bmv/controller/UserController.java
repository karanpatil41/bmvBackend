package com.bmv.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bmv.entities.Role;
import com.bmv.entities.User;
import com.bmv.model.JwtRequest;
import com.bmv.model.JwtResponse;
import com.bmv.payloads.UserSignUpRequest;
import com.bmv.security.JwtHelper;
import com.bmv.services.UserService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/user")
public class UserController {

	private Logger logger = LoggerFactory.getLogger(OncePerRequestFilter.class);

	@Autowired
	private UserService userService;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager manager;

	@Autowired
	private JwtHelper helper;

	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
		logger.info("AUTHENTICATION-0-In User Controller's login()", request);
		this.doAuthenticate(request.getEmail(), request.getPassword());

		logger.info("AUTHENTICATION-6-In User Controller's login()");
		UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
		logger.info("AUTHENTICATION-9-In User Controller's login(). userDetails: " + userDetails);
		String token = this.helper.generateToken(userDetails);
		logger.info("AUTHENTICATION-13-In User Controller's login(). token: " + token);

		JwtResponse response = JwtResponse.builder().jwtToken(token).username(userDetails.getUsername()).build();
		logger.info("AUTHENTICATION-14-In User Controller's login(). response: " + response);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	private void doAuthenticate(String email, String password) {
		logger.info(
				"AUTHENTICATION-1-In User Controller's doAuthenticate(). email: " + email + " password: " + password);
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
		try {
			logger.info("AUTHENTICATION-2-In User Controller's doAuthenticate(). authentication: " + authentication);
			manager.authenticate(authentication);
			logger.info("AUTHENTICATION-5-In User Controller's doAuthenticate(). authentication: " + authentication);

		} catch (BadCredentialsException e) {
			logger.info("AUTHENTICATION--In User Controller's doAuthenticate()");
			throw new BadCredentialsException(" Invalid Username or Password  !!");
		}

	}

	@ExceptionHandler(BadCredentialsException.class)
	public String exceptionHandler() {
		return "Credentials Invalid !!";
	}

	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody UserSignUpRequest userSignUpRequest) {
		System.out.println("In User controller =" + userSignUpRequest.toString());
		try {
			// You can convert the UserSignUpRequest to the User entity and save it
			User user = convertToUserEntity(userSignUpRequest);
			User userCreated = userService.createUser(user);
			logger.info("In UserController's signup(): " + userCreated.toString());

			return new ResponseEntity<>("User signed up successfully", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("Error signing up user", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	private User convertToUserEntity(UserSignUpRequest userSignUpRequest) {
		// Convert UserSignUpRequest to User entity
		User user = new User();
		user.setFirstName(userSignUpRequest.getFirstName());
		user.setLastName(userSignUpRequest.getLastName());
		user.setEmail(userSignUpRequest.getEmail());
		user.setContactNumber(userSignUpRequest.getContactNumber());
		user.setAddress(userSignUpRequest.getAddress());
		user.setPassword(userSignUpRequest.getPassword());
		user.setConfirmPassword(userSignUpRequest.getConfirmPassword());
		user.setRoleName(userSignUpRequest.getRoleName());

		// Fetch the Role entity based on roleId and set it to the user
		Role role = new Role();
		role.setRoleId(userSignUpRequest.getRoleId()); // Assuming roleId is an int in UserSignUpRequest
//		user.setRole(role);
		return user;
	}

	@GetMapping("/userProfile")
	public ResponseEntity<User> getUserByUsername(@RequestParam("username") String username) {

		logger.info("UserController's getUserByUsername");
		User user = userService.getUserByUsername(username);
		logger.info("UserController's getUserByUsername response= " + user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
//	@PutMapping("/updateProfile")
	@PatchMapping("/updateProfile")
	public ResponseEntity<String> updateUser(@RequestParam("username") String username,
			@RequestBody Map<String, Object> updates) {
		logger.info("UserController's updateUser() updates="+updates);
		logger.info("UserController's updateUser() updates="+updates.toString());
	    
		User updatedUser = userService.updateUser(username, updates);
		logger.info("UserController's updateUser() updatedUser=",updatedUser);
		return new ResponseEntity<String>("User updated successfully", HttpStatus.OK);
	}

	@GetMapping("/user")
	public List<User> getUser() {
		System.out.println("getting user");
		List<User> usersList = this.userService.getAllUsers();

		return usersList;
	}

	@GetMapping("/user/userProfile")
	public String userProfile() {
		return "Welcome to User Profile";
	}

	@GetMapping("/current-user")
	public String getLoggedInUser(Principal principal) {
		return principal.getName();
	}

}

package com.bmv.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bmv.entities.Role;
import com.bmv.entities.User;
import com.bmv.model.JwtRequest;
import com.bmv.model.JwtResponse;
import com.bmv.payloads.UserSignUpRequest;
import com.bmv.security.JwtHelper;
import com.bmv.services.UserService;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class UserController {

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

	        this.doAuthenticate(request.getEmail(), request.getPassword());


	        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
	        String token = this.helper.generateToken(userDetails);

	        JwtResponse response = JwtResponse.builder()
	                .jwtToken(token)
	                .username(userDetails.getUsername()).build();
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    }

	    private void doAuthenticate(String email, String password) {

	        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
	        try {
	            manager.authenticate(authentication);


	        } catch (BadCredentialsException e) {
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
			userService.createUser(user);

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

		// Fetch the Role entity based on roleId and set it to the user
		Role role = new Role();
		role.setRoleId(userSignUpRequest.getRoleId()); // Assuming roleId is an int in UserSignUpRequest
		user.setRole(role);
		return user;
	}

	@GetMapping("/user")
	public List<User> getUser() {
		System.out.println("getting user");
		List<User> usersList = this.userService.getAllUsers();
		return usersList;
	}

	@GetMapping("/user/userProfile")
//	@PreAuthorize("hasAuthority('NORMAL_USER')")
	public String userProfile() {
		return "Welcome to User Profile";
	}

	@GetMapping("/current-user")
	public String getLoggedInUser(Principal principal){
		return principal.getName();
	}
//	@GetMapping("/venueManager/venueManagerProfile")
//	@PreAuthorize("hasAuthority('VENUE_MANAGER')")
//	public String venueManagerProfile() {
//		return "Welcome to Venue Manager Profile";
//	}
//	
//	@GetMapping("/admin/adminProfile")
//	@PreAuthorize("hasAuthority('ADMIN')")
//	public String adminProfile() {
//		return "Welcome to Admin Profile";
//	}

}

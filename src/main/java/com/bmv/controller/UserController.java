package com.bmv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bmv.entities.Role;
import com.bmv.entities.User;
import com.bmv.payloads.UserSignUpRequest;
import com.bmv.services.UserService;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

//	@PostMapping("/signup")
//	public ResponseEntity<String>  signup(@RequestBody User user){
//		System.out.println("In User controller ="+user.toString());
//		
//		Role role = user.getRole();
//		
//		System.out.println("In User controller");
//		User userAdded = this.userService.saveUser(user);
//		String msg = "Signup successfully done!!";
//		return new ResponseEntity<String>(msg, HttpStatus.CREATED);
//	}
	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody UserSignUpRequest userSignUpRequest) {
		System.out.println("In User controller =" + userSignUpRequest.toString());
		try {
			// You can convert the UserSignUpRequest to the User entity and save it
			User user = convertToUserEntity(userSignUpRequest);
			userService.saveUser(user);
			
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
}

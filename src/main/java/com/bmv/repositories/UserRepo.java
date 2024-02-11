package com.bmv.repositories;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.bmv.entities.User;
//import com.bmv.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	public Optional<User> findByEmail(String email);
	
	@Query("select u from User u where u.email=:email")
	public User getUserByUserName(@Param("email") String email);
	
	@Procedure(name = "getUserByUsername")
	public User getUserByUsername(String email);
}

package com.bmv.repositories;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;

import com.bmv.entities.User;
//import com.bmv.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	public Optional<User> findByEmail(String email);
}

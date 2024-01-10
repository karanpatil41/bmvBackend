package com.bmv.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bmv.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}

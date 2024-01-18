package com.bmv.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bmv.entities.Usee;

public interface UserRepository extends JpaRepository<Usee, Integer>{
	public Optional<Usee> findByEmail(String email);
}

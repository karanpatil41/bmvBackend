package com.bmv.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.bmv.entities.User;
import com.bmv.entities.Venue;

public interface VenueRepo extends JpaRepository<Venue, Integer> {
	
	List<Venue> findByAddressAndCapacity(String address, Integer capacity);
	
//	@Query(value=  "CALL getVenueByUsername(:email)", nativeQuery = true)
//	List<Venue> findByUsername(@Param("email") String email);
	
	@Procedure(name = "getVenueByUsername")
	public List<Venue> getVenueByUsername(@Param("p_email") String email);
	
//	@Query("select v from Venue v where v.username=:username")
//	public List<Venue> getVenueByUsername(@Param("username") String username);
	

}

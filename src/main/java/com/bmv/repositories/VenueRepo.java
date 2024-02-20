package com.bmv.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.bmv.dto.VenueDTO;
import com.bmv.entities.Venue;

public interface VenueRepo extends JpaRepository<Venue, Integer> {
	
	List<Venue> findByAddressAndCapacity(String address, Integer capacity);
	
//	@Query(value=  "CALL getVenueByUsername(:email)", nativeQuery = true)
//	List<Venue> findByUsername(@Param("email") String email);
	
	@Procedure(name = "getVenueByUsername")
	public List<Venue> getVenueByUsername(@Param("p_email") String email);
	
//	@Query("select v from Venue v where v.username=:username")
//	public List<Venue> getVenueByUsername(@Param("username") String username);

//	select venue.venue_name,venue.amount,user.email,user.first_name,user.last_name from venue inner join user on venue.venue_manager_id=user.id;
	@Query("SELECT v.id, v.venueName, v.address, v.amount, u.email, u.firstName, u.lastName,u.contactNumber FROM Venue v INNER JOIN v.venueManagerId u WHERE v.id=:id")
	Object findVenueAndUserData(@Param("id") Integer id);
	
//	1st query--select id,venue_name,amount,address from venue where venue_manager_id=14; //You will get list of venues of userid=14
	@Query("SELECT new com.bmv.dto.VenueDTO(v.id, v.venueName, v.amount, v.address) FROM Venue v WHERE v.venueManagerId.id =:venueManagerId")
	List<VenueDTO> findByVenueManagerIdId(@Param("venueManagerId") int venueManagerId);

}

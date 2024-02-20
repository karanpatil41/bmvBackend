package com.bmv.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bmv.dto.BookingDto;
import com.bmv.dto.UserDto;
import com.bmv.entities.Booking;

public interface BookingRepo extends JpaRepository<Booking, Integer> {

	@Query("SELECT new com.bmv.dto.BookingDto(b.bookingId,b.venue.id, b.bookingDate, b.checkinDate, b.checkoutDate, b.noOfGuests, b.paymentStatus) FROM Booking b WHERE b.venue.id = :venueId")
	List<BookingDto> findByVenueIdId(@Param("venueId") int venueId);

	@Query("SELECT new com.bmv.dto.UserDto(u.id, u.address, u.contactNumber, u.email, u.firstName, u.lastName, b.bookingId) " +
		       "FROM Booking b JOIN b.user u " +
		       "WHERE b.bookingId = :bookingId")
	List<UserDto> findUserDetailsByBookingId(@Param("bookingId") int bookingId);
}

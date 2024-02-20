package com.bmv.services;

import java.util.List;

import com.bmv.dto.BookingDto;
import com.bmv.dto.UserDto;
import com.bmv.entities.Booking;

public interface BookingService {
	Booking bookVenue(Booking booking);
	
	List<BookingDto> getBookingsByVenueId(int venueId);
	
	List<UserDto> getUserDetailsByBookingId(int userId);

	void acceptBooking(int bookingId);
	
	void rejectBooking(int bookingId);
}

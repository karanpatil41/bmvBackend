package com.bmv.services.impl;

import java.util.List;

import javax.ws.rs.NotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bmv.dto.BookingDto;
import com.bmv.dto.UserDto;
import com.bmv.entities.Booking;
import com.bmv.repositories.BookingRepo;
import com.bmv.services.BookingService;

@Service
public class BookingServiceImpl implements BookingService {

	private Logger logger = LoggerFactory.getLogger(OncePerRequestFilter.class);
	
	@Autowired
	private BookingRepo bookingRepo;
	
	@Override
	public Booking bookVenue(Booking booking) {
		logger.info("BookingServiceImpl--bookVenue()--booking="+booking);
		Booking savedBooking= bookingRepo.save(booking);
		logger.info("BookingServiceImpl--bookVenue()--savedBooking="+savedBooking);
		return savedBooking;
	}
	public List<BookingDto> getBookingsByVenueId(int venueId) {
		logger.info("BookingServiceImpl--getBookingsByVenueId()");
		List<BookingDto> bookingListByVenueId =  bookingRepo.findByVenueIdId( venueId);
		return bookingListByVenueId;
	}
	
	public List<UserDto> getUserDetailsByBookingId(int userId){
		logger.info("BookingServiceImpl--getUserDetailsByBookingId()");

		return bookingRepo.findUserDetailsByBookingId(userId);
	}
	@Override
	public void acceptBooking(int bookingId) {
		// Retrieve the booking by its ID
		Booking booking = bookingRepo.findById(bookingId).orElseThrow(() -> new NotFoundException("Booking Not found"));
		
		// Update the payment status to "Done"
		booking.setPaymentStatus("Done");
		
		//Save the updated booking
		bookingRepo.save(booking);
	}
	@Override
	public void rejectBooking(int bookingId) {
		// Retrieve the booking by its ID
				Booking booking = bookingRepo.findById(bookingId).orElseThrow(() -> new NotFoundException("Booking Not found"));
				
				// Update the payment status to "Done"
				booking.setPaymentStatus("Rejected");
				
				//Save the updated booking
				bookingRepo.save(booking);
		
	}
}

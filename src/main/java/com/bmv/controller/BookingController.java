package com.bmv.controller;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bmv.entities.Booking;
import com.bmv.services.BookingService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/booking")
public class BookingController {
	
	private Logger logger = LoggerFactory.getLogger(OncePerRequestFilter.class);
	
	@Autowired
	private BookingService bookingService;
	
	@PostMapping("/book")
	public ResponseEntity<String> bookVenue(@RequestBody Booking booking){
		booking.setCreatedDate(LocalDateTime.now());
		booking.setBookingDate(LocalDateTime.now());
		logger.info("BookingController--bookVenue()--booking="+booking);	
		Booking savedBooking = bookingService.bookVenue(booking);
		logger.info("BookingController--bookVenue()--booking="+savedBooking);
		return new ResponseEntity<String>("Pay for confirmation",HttpStatus.OK);
	}
	
}

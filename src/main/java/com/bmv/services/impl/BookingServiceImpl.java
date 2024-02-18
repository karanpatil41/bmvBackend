package com.bmv.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

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

}

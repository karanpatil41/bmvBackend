package com.bmv.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bmv.entities.Booking;

public interface BookingRepo extends JpaRepository<Booking, Integer> {
	
}

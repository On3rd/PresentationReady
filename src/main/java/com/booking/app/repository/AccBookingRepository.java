package com.booking.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.booking.app.modal.AccBooking;


public interface AccBookingRepository extends JpaRepository<AccBooking, Long> {

	
}

package com.booking.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.booking.app.modal.Property;

public interface PropertyRepository extends JpaRepository<Property, Long> {

}

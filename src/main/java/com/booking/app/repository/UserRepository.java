package com.booking.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.booking.app.modal.User;

public interface UserRepository extends JpaRepository<User, Long> {

	 
}

package com.booking.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.booking.app.modal.UserContactInformation;

public interface UserContactInformationRepository extends JpaRepository<UserContactInformation, Long>{

}
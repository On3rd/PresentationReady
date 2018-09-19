package com.booking.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.booking.app.modal.UserPaymentMethod;

public interface UserPaymentMethodRepository  extends JpaRepository<UserPaymentMethod, Long> {

}

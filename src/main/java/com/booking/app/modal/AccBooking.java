package com.booking.app.modal;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

@Entity
@Table(name = "accBookingTable")
@JsonIgnoreType
public class AccBooking {


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	private Long accBooking_Id;
	private Date checkInDate;
	private Date checkOutDate;
	private int noOfVisitors;
	private int nights;
	
	@OneToOne
	private Property property;
	
	@OneToOne
	private User user;
	
	public Date getCheckInDate() {
		return checkInDate;
	}
	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}
	public Date getCheckOutDate() {
		return checkOutDate;
	}
	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
	public int getNoOfVisitors() {
		return noOfVisitors;
	}
	public void setNoOfVisitors(int noOfVisitors) {
		this.noOfVisitors = noOfVisitors;
	}
	public int getNights() {
		return nights;
	}
	public void setNights(int nights) {
		this.nights = nights;
	}
	public Property getProperty() {
		return property;
	}
	public void setProperty(Property property) {
		this.property = property;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public AccBooking(Date checkInDate, Date checkOutDate, int noOfVisitors, int nights, Property property, User user) {
		super();
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.noOfVisitors = noOfVisitors;
		this.nights = nights;
		this.property = property;
		this.user = user;
	}
	public AccBooking() {
		
	}
	
	

	

	
	
	
}


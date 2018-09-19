package com.booking.app.controller;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.booking.app.modal.AccBooking;
import com.booking.app.services.AccBookingService;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins ="http://localhost:4200",allowedHeaders = "*")

public class AccBookingController {

	
	@Autowired 
	private AccBookingService accBookingService;
	
	@RequestMapping(path = "/accBooking",method = RequestMethod.GET,produces = "application/json")
	public List<AccBooking> getAccBooking()
	{
		return accBookingService.getAccBooking();
	}
	
	@RequestMapping(path = "/accBooking/{accBooking_Id}",method = RequestMethod.GET,produces = "application/json")
	public Optional<AccBooking> getAccBooking(@PathVariable Long accBooking_Id)
	{
		return accBookingService.getAccBooking(accBooking_Id);
	}
	
	@RequestMapping(path = "/accBooking/{accBooking_Id}", method = RequestMethod.DELETE,consumes = "application/json")
	public boolean deleteAccBooking(@PathVariable Long accBooking_Id)
	{
		return accBookingService.deleteAccBooking(accBooking_Id);
	}

	@RequestMapping(path = "/accBooking",method = RequestMethod.POST,consumes = "application/json")
	public AccBooking createAccBooking(@RequestBody AccBooking accBooking)
	{
		return accBookingService.createAccBooking(accBooking);
	}

	@RequestMapping(path = "/accBooking",method = RequestMethod.PUT,consumes = "application/json")
	public AccBooking updateAccBooking(@RequestBody AccBooking accBooking)
	{
		return accBookingService.updateAccBooking(accBooking);
	}
	
	@RequestMapping(path = "/bookAccomodation/{token}/{property_id}/{visitors}/{checkInDate}/{checkOutDate}",method = RequestMethod.POST, consumes ="applicaiton/json")
	public AccBooking bookAccommodation(@PathVariable String token, @PathVariable Long property_id,@PathVariable int visitors,@PathVariable Date checkInDate, @PathVariable Date checkOutDate)
	{
		return accBookingService.bookAccommodation(token, property_id, visitors, checkInDate, checkOutDate);
	}
	
	@RequestMapping(path = "/viewBookings/{token}",method = RequestMethod.GET,produces = "application/json")
	public List<AccBooking> viewBooking(@PathVariable String token)
	{
		return accBookingService.viewBooking(token);
	}
	

	
	
}

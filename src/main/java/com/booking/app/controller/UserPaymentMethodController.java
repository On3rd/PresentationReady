package com.booking.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.booking.app.modal.UserPaymentMethod;
import com.booking.app.services.UserPaymentMethodService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins ="http://localhost:4200",allowedHeaders = "*")

public class UserPaymentMethodController {

	
	@Autowired
	private UserPaymentMethodService userPaymentMethodService;
	
	
	@RequestMapping(path = "/userPaymentMethod",method = RequestMethod.GET,produces = "application/json")
	public List<UserPaymentMethod> getUserPaymentMethod()
	{
		return userPaymentMethodService.getUserPaymentMethod();
	}
	
	@RequestMapping(path ="/userPaymentMethod/{token}",method = RequestMethod.GET,produces ="application/json")
	public Optional<UserPaymentMethod> getUserPaymentMethod(@PathVariable String token)
	{
		return userPaymentMethodService.getUserPaymentMethod(token);
	}
	
	@RequestMapping(path ="/userPaymentMethod/{token}",method = RequestMethod.DELETE,consumes = "application/json")
	public boolean deleteUserPaymentMethod(@PathVariable String token)
	{
		return userPaymentMethodService.deleteUserPaymentMethod(token);
	}
	
	@RequestMapping(path ="/confirmPaymentMethod/{token}",method = RequestMethod.GET,produces = "applications/json")
	public boolean confirmPaymentMethod(@PathVariable String token)
	{
		return userPaymentMethodService.confirmPaymentMethod(token);
	}

	@RequestMapping(path ="/createUserPayment/{token}",method = RequestMethod.POST,consumes = "application/json")
	public UserPaymentMethod createUserPaymentMethod(@RequestBody UserPaymentMethod userPaymentMethod,@PathVariable String token)
	{
		return userPaymentMethodService.createUserPaymentMethod(userPaymentMethod, token);
	}

	@RequestMapping(path ="/userPaymentMethod/{token}",method = RequestMethod.PUT,consumes = "appliaction/json")
	public UserPaymentMethod updateUserPaymentMethod(@RequestBody UserPaymentMethod userPaymentMethod,@PathVariable String token) throws Exception
	{
		return userPaymentMethodService.updateUserPaymentMethod(userPaymentMethod,token);
	}
	
	 
}

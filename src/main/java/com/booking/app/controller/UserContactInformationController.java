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

import com.booking.app.modal.UserContactInformation;
import com.booking.app.services.UserContactInformationService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins ="http://localhost:4200",allowedHeaders = "*")

public class UserContactInformationController {

	@Autowired
	private UserContactInformationService userContactInformationService;
	
	@RequestMapping(path = "/userContactInformation",method = RequestMethod.GET,produces = "application/json")
	public List<UserContactInformation> getUserContactInformation()
	{
		return userContactInformationService.getUserContactInformation();
	}
	
	@RequestMapping(path = "/getUserContactInformation/{token}" , method = RequestMethod.GET, produces ="application/json")
	public Optional<UserContactInformation> getUserContactInformation(@PathVariable String token)
	{
		return userContactInformationService.getUserContactInformation(token);
	}
	
	@RequestMapping(path = "/deleteUserContactInformation/{token}", method = RequestMethod.DELETE,consumes = "application/json")
	public boolean deleteUserContactInformation(@PathVariable String token)
	{
		return userContactInformationService.deleteUserContactInformation(token);
	}

	@RequestMapping(path = "/postUserContactInformation/{token}",method =RequestMethod.POST,consumes = "application/json")
	public UserContactInformation createUserContactInformation(@RequestBody UserContactInformation userContactInformation,@PathVariable String token) 
	{
		
		return userContactInformationService.createUserContactInformation(userContactInformation,token);
	}

	@RequestMapping(path = "/putUserContactInformation/{token}",method = RequestMethod.PUT,consumes ="appliacion/json")
	public UserContactInformation updateUserContactInformation(@RequestBody UserContactInformation userContactInformation,@PathVariable String token )
	{
		return userContactInformationService.updateUserContactInformation(userContactInformation,token);
	}
	
	 
	
	
}

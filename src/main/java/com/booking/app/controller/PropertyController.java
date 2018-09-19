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

import com.booking.app.modal.AccBooking;
import com.booking.app.modal.Property;
import com.booking.app.services.PropertyService;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins ="http://localhost:4200",allowedHeaders = "*")

public class PropertyController {

	@Autowired
	private PropertyService propertyService;
	
	@RequestMapping(path = "/property", method = RequestMethod.GET,produces = "application/json")
	public List<Property> getProperties()
	{
		return propertyService.getProperties();
	}
	
	@RequestMapping(path = "/property/{prob_Id}/{token}" , method = RequestMethod.GET ,produces = "application/json")
	public Optional<Property> getProperty(@PathVariable Long prob_Id)
	{
		return propertyService.getProperty(prob_Id);
	}
	
	@RequestMapping(path = "/property/{prob_Id}/{token}",method = RequestMethod.DELETE,consumes ="application/json")
	public boolean deleteProperty(@PathVariable Long prob_Id) throws Exception 
	{
		return propertyService.deleteProperty(prob_Id);
	}
	
	@RequestMapping(path = "/listProperty/{token}",method = RequestMethod.POST,consumes = "application/json")
	public Property createProperty(@RequestBody Property property,@PathVariable String token) 
	{
		return propertyService.createProperty(property, token);
	}

	@RequestMapping(path = "/property/{prop_id}/{token}",method = RequestMethod.PUT,consumes = "application/json")
	public Property updateProperty(@RequestBody Property property,@PathVariable Long prop_id,@PathVariable String token) throws Exception 
	{
		return propertyService.updateProperty(property,prop_id,token);
	}
	
	@RequestMapping(path = "/ownedProperties/{token}",method = RequestMethod.GET,produces = "application/json")
	public List<Property> ownedProperties(@PathVariable String token)
	{
		return propertyService.ownedProperties(token);
	}
	
	@RequestMapping(path = "/checkOwnership/{token}" , method = RequestMethod.GET,produces = "application/json")
	public boolean checkOwnership(@PathVariable String token)
	{
		return propertyService.checkOwnership(token);
	}
	
	@RequestMapping(path = "/searchProperty/{title}/{rooms}/{visitors}",method = RequestMethod.GET,produces = "application/json")
	public List<Property> searchProperty(@PathVariable String title,@PathVariable int rooms,@PathVariable int visitors) throws Exception 
	{
		return propertyService.searchProperty(title, rooms, visitors);
	}
	
	@RequestMapping(path = "/displayProperty/{title}",method = RequestMethod.GET,produces = "application/json")
	public List<Property> displayProperty(@PathVariable String title)
	{
		return propertyService.displayProperty(title);
	}
	
	 @RequestMapping(path = "/displayAvarage/{title}",method = RequestMethod.GET,produces = "application/json")
	 public double getAvarage(@PathVariable String title)
	 {
		return propertyService.getAvarage(title); 
	 }
	 
	 @RequestMapping(path = "/viewReservations/{property_Id}/{token}",method = RequestMethod.GET,produces = "application/json")
	 public List<AccBooking> viewReservations(@PathVariable int property_Id,@PathVariable String token)
	 {
		 return propertyService.viewReservations(property_Id, token);
	 }
	 
	 @RequestMapping(path = "/countProperties/{title}",method = RequestMethod.GET,produces = "application/json")
	 public int countProperties(@PathVariable String title)
	 { 
		return propertyService.countProperties(title);
	 }
	 
	 
	 @RequestMapping(path = "/possibleEntry/{title}", method = RequestMethod.GET,produces = "application/json")
	 public List<String> getPossibleEntry(@PathVariable String title)
	{
		return propertyService.getPossibleEntry(title);
	}
	 
	 
	
}

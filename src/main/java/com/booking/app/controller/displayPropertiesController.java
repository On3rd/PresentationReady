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

import com.booking.app.modal.DisplayProperties;
import com.booking.app.services.DisplayPropertiesService;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins ="http://localhost:4200",allowedHeaders = "*")

public class displayPropertiesController {


	@Autowired
	private DisplayPropertiesService displayPropertiesService;
	
	@RequestMapping(path = "/displayProperties",method = RequestMethod.GET,produces = "application/json")
	public List<DisplayProperties> getDisplayProperties()
	{
		return displayPropertiesService.getDisplayProperties();
	}
	
	@RequestMapping(path = "/displayProperties/{disp_Id}", method = RequestMethod.GET,produces = "application/json")
	public Optional<DisplayProperties> getDisplayProperty(@PathVariable Long disp_Id)
	{
		return displayPropertiesService.getDisplayProperty(disp_Id);
	}
	
	@RequestMapping(path ="/displayProperties/{disp_Id}",method = RequestMethod.DELETE,consumes = "application/json")
	public boolean deleteDisplayProperties(@PathVariable Long disp_Id)
	{
		
		return displayPropertiesService.deleteDisplayProperties(disp_Id);
	}

	@RequestMapping(path = "/displayProperties",method = RequestMethod.POST,consumes ="application/json")
	public DisplayProperties createDisplayProperties(@RequestBody DisplayProperties displayProperties)
	{
		return displayPropertiesService.createDisplayProperties(displayProperties);
	}
	
	@RequestMapping(path = "/calcAverage/{city}",method = RequestMethod.GET,produces = "application/json")
	public double calculateAverage(@PathVariable String city)
	{
		return displayPropertiesService.calcAvarage(city);
	}

	@RequestMapping(path = "/calcProperties/{city}",method = RequestMethod.GET,produces = "application/json")
	public double calculateProperties(@PathVariable String city)
	{
		return displayPropertiesService.numberOfProperties(city);
	}
	
	@RequestMapping(path = "/displayProperties",method = RequestMethod.PUT,consumes = "application/json")
	public DisplayProperties updateDisplayProperties(@RequestBody DisplayProperties displayProperties)
	{
		return displayPropertiesService.updateDisplayProperties(displayProperties);
	}
	
	 

	
	
}

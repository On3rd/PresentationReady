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

import com.booking.app.modal.PropertyTimeSheet;
import com.booking.app.services.PropertyTimeSheetService;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins ="http://localhost:4200",allowedHeaders = "*")

public class PropertyTimeSheetController {

	@Autowired
	 
	private PropertyTimeSheetService propertyTimeSheetService;
	
	@RequestMapping(path = "/propertyTimeSheet",method = RequestMethod.GET,produces = "application/json")
	public List<PropertyTimeSheet> getPropertyTimeSheet()
	{
		return propertyTimeSheetService.getPropertyTimeSheet();
	}
	
	@RequestMapping(path = "/propertyTimeSheet/{PropTimeSheetId}",method = RequestMethod.GET,produces = "application/json")
	public Optional<PropertyTimeSheet> getPropertyTimeSheet(@PathVariable Long PropTimeSheetId)
	{
		return propertyTimeSheetService.getPropertyTimeSheet(PropTimeSheetId);
	}
	
	@RequestMapping(path = "/propertyTimeSheet/{PropTimeSheetId}",method = RequestMethod.DELETE,consumes = "application/json")
	public boolean deletePropertyTimeSheet(@PathVariable Long PropTimeSheetId)
	{
		return propertyTimeSheetService.deletePropertyTimeSheet(PropTimeSheetId);
	}

	@RequestMapping(path = "/propertyTimeSheet/{from}/{until}/{prop_id}",method = RequestMethod.POST,consumes = "application/json")
	public PropertyTimeSheet createPropertyTimeSheet(@PathVariable Date from,@PathVariable Date until,@PathVariable Long prop_id)
	{
		return propertyTimeSheetService.createPropertyTimeSheet(from,until,prop_id);
	}
	
	@RequestMapping(path = "/propertyTimeSheets/{from}/{until}/{prop_id}",method = RequestMethod.POST,consumes = "application/json")
	public PropertyTimeSheet createPropertyTimeSheets(@PathVariable String from,@PathVariable String until,@PathVariable Long prop_id)
	{
		return propertyTimeSheetService.createPropertyTimeSheets(from,until,prop_id);
	}

	@RequestMapping(path = "/propertyTimeSheet",method = RequestMethod.PUT,consumes = "application/json")
	public PropertyTimeSheet updatePropertyTimeSheet(@RequestBody PropertyTimeSheet propertyTimeSheet)
	{
		return propertyTimeSheetService.updatePropertyTimeSheet(propertyTimeSheet);
	}
	
	
	@RequestMapping(path = "/viewAvailability/{prod_id}",method = RequestMethod.GET,produces = "application/json")
	public List<PropertyTimeSheet> getAvailability(@PathVariable Long prod_id)
	{
		
		return propertyTimeSheetService.getAvailability(prod_id);
	}
	
	
	
	 

	
	
}

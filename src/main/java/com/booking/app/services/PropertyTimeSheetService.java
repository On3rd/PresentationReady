package com.booking.app.services;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.app.modal.Property;
import com.booking.app.modal.PropertyTimeSheet;
import com.booking.app.repository.PropertyRepository;
import com.booking.app.repository.PropertyTimeSheetRepository;

@Service
@Transactional
public class PropertyTimeSheetService {

	

@Autowired

private PropertyTimeSheetRepository propertyTimeSheetRepository;

@Autowired
private PropertyRepository propertyRepository;


public List<PropertyTimeSheet> getPropertyTimeSheet()
{
	return propertyTimeSheetRepository.findAll();
}


public Optional<PropertyTimeSheet> getPropertyTimeSheet(Long PropTimeSheetId)
{
	return propertyTimeSheetRepository.findById(PropTimeSheetId);
}

public boolean deletePropertyTimeSheet(Long PropTimeSheetId)
{
	propertyTimeSheetRepository.deleteById(PropTimeSheetId);
	return true;
}

public PropertyTimeSheet createPropertyTimeSheet(Date starts ,Date ends, Long property_id)
{
	Calendar start = new GregorianCalendar();
	Calendar end= new GregorianCalendar();
	Property property = new Property();
	PropertyTimeSheet propertyTimeSheet = new PropertyTimeSheet();
	start.setTime(starts);
	end.setTime(ends);
	
	while(start.before(end))
	{
		start.add(Calendar.DAY_OF_MONTH, 1);
		propertyTimeSheet.setAvailableDates(start);
		
		for(int x = 0; x < propertyRepository.findAll().size();x++)
		{
			if(propertyRepository.findAll().get(x).getProp_Id()== property_id)
			{
				property = propertyTimeSheetRepository.findAll().get(x).getProperty();
			}
		}
		propertyTimeSheet.setProperty(property);
		propertyTimeSheetRepository.save(propertyTimeSheet);
	}
	return null;
}

public PropertyTimeSheet createPropertyTimeSheets(String startz ,String endz, Long property_id) 
{
	Calendar start = new GregorianCalendar();
	Calendar end = new GregorianCalendar();
	Property property = new Property();
	DateFormat df = DateFormat.getInstance();
	
	PropertyTimeSheet propertyTimeSheet = new PropertyTimeSheet();
	df.format(startz);
	df.format(endz);
	
	
	
	while(start.before(end))
	{
		start.add(Calendar.DAY_OF_MONTH, 1);
		propertyTimeSheet.setAvailableDates(start);
		
		for(int x = 0; x < propertyRepository.findAll().size();x++)
		{
			if(propertyRepository.findAll().get(x).getProp_Id()== property_id)
			{
				property = propertyTimeSheetRepository.findAll().get(x).getProperty();
			}
		}
		propertyTimeSheet.setProperty(property);
		propertyTimeSheetRepository.save(propertyTimeSheet);
	}
	return null;
}


public PropertyTimeSheet updatePropertyTimeSheet(PropertyTimeSheet propertyTimeSheet)
{
	return propertyTimeSheetRepository.save(propertyTimeSheet);
}


public List<PropertyTimeSheet> getAvailability(Long prod_id)
{
	List<PropertyTimeSheet> propertyTimeSheet = new ArrayList<>();
	
	if(propertyTimeSheetRepository.findAll().isEmpty())
	{
		propertyTimeSheet = null;;
	}else
	{
		for(int index = 0 ; index < propertyTimeSheetRepository.findAll().size();index++ ) {
			
			if(propertyTimeSheetRepository.findAll().get(index).getProperty().getProp_Id() == prod_id)
			{
				propertyTimeSheet.add(propertyTimeSheetRepository.findAll().get(index));
			}
			else
			{
				propertyTimeSheet =null;
			}
			
		}
	}
	
	return propertyTimeSheet;
}



 
}

package com.booking.app.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.app.modal.DisplayProperties;
import com.booking.app.repository.DisplayPropertiesRepository;
import com.booking.app.repository.PropertyRepository;

@Service
public class DisplayPropertiesService {



	@Autowired
	private DisplayPropertiesRepository displayPropertiesRepository;
	
	@Autowired
	private PropertyRepository propertyRepository;
	
	
	public List<DisplayProperties> getDisplayProperties()
	{
		return displayPropertiesRepository.findAll();
	}
	
	public Optional<DisplayProperties> getDisplayProperty(Long disp_Id)
	{
		return displayPropertiesRepository.findById(disp_Id);
	}
	
	public boolean deleteDisplayProperties(Long disp_Id)
	{
		displayPropertiesRepository.deleteById(disp_Id);
		return true;
	}

	public DisplayProperties createDisplayProperties(DisplayProperties displayProperties)
	{
		return displayPropertiesRepository.save(displayProperties);
	}

	public Double calcAvarage(String city)
	{
		double average= 0;
		if(propertyRepository.findAll() != null)
		{
			for(int x = 0;x < propertyRepository.findAll().size();x++)
			{
				if(propertyRepository.findAll().get(x).getCity().equals(city.toUpperCase().trim()))
				{
					average = average + propertyRepository.findAll().get(x).getPrice();
				}
			}
			return average/this.numberOfProperties(city);
		}
		
		return null;
	}
	
	public int numberOfProperties(String city)
	{
		int sum = 0;
		
		if(propertyRepository.findAll() != null)
		{
		for(int x = 0;x < propertyRepository.findAll().size();x++)
		{
			if(propertyRepository.findAll().get(x).getCity().equals(city.toUpperCase().trim()))
			{
				sum++;
			}
		}
		return sum;
		}
		return 0;
	}
	
	public DisplayProperties updateDisplayProperties(DisplayProperties displayProperties)
	{
		return displayPropertiesRepository.save(displayProperties);
	}
	
	 
}
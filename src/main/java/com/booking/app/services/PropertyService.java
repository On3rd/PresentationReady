package com.booking.app.services;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.app.modal.AccBooking;
import com.booking.app.modal.Property;
import com.booking.app.modal.User;
import com.booking.app.repository.AccBookingRepository;
import com.booking.app.repository.PropertyRepository;
import com.booking.app.repository.UserRepository;

@Service
@Transactional
public class PropertyService {

@Autowired
private PropertyRepository propertyRepository;

@Autowired
private UserRepository userRepository;
 
@Autowired
private UserService userService;

@Autowired
private AccBookingRepository accBookingRepository;

DecimalFormat df = new DecimalFormat("0.00"); 

public List<Property> getProperties()
{
	return propertyRepository.findAll();
}

public Optional<Property> getProperty(Long prob_Id)
{
	return propertyRepository.findById(prob_Id);
}

public boolean deleteProperty(Long prop_Id) throws Exception
{
	Property property = propertyRepository.getOne(prop_Id);
	
	if(property == null)
	{
		throw new Exception("Property doesnt exists");
	}
	else
	{
		propertyRepository.deleteById(prop_Id);
		return true;
	}
	
}

public Property createProperty(Property property,String token)
{
	Long user_id = userService.getUserID(token);
	User user = userRepository.getOne(user_id);
	if(user_id != null )
	{
		for(int x = 0 ; x < propertyRepository.findAll().size();x++)
		{
			if(propertyRepository.findAll().get(x).getProp_name().equals(property.getProp_name()))
			{
				return null;
			}
			
		}
		
			property.setUser(user);
			return propertyRepository.save(property);
		
	}
	
	return null;
}

public Property updateProperty(Property property,Long prop_Id,String token) throws Exception
{
	Property updatedProperty = propertyRepository.getOne(prop_Id);
	Long user_Id = userService.getUserID(token);
	
	if(updatedProperty != null && updatedProperty.getUser().getUser_Id().equals(user_Id))
	{
		updatedProperty.setProp_name(property.getProp_name());
		updatedProperty.setProp_type(property.getProp_type());
		updatedProperty.setCity(property.getCity());
		updatedProperty.setAddressline2(property.getAddressline2());
		updatedProperty.setStreetAddress(property.getStreetAddress());
		updatedProperty.setWebsite(property.getWebsite());
		updatedProperty.setNumberRoom(property.getNumberRoom());
		updatedProperty.setCountry(property.getCountry());
		updatedProperty.setPrice(property.getPrice());
		updatedProperty.setProvince(property.getProvince());
		updatedProperty.setPostal_code(property.getPostal_code());
		updatedProperty.setDisplayImage(property.getDisplayImage());
		
		return propertyRepository.save(updatedProperty);	
	}
	else
	{
		throw new Exception("Update Failed");
	}

}


public List<Property> ownedProperties(String token)
{
	Long user_Id = userService.getUserID(token);
	List<Property> properties = new ArrayList<>();
	
	for(int index = 0;index <propertyRepository.findAll().size();index++ )
	{
		if(propertyRepository.findAll().get(index).getUser().getUser_Id() == user_Id )
		{
			properties.add(propertyRepository.findAll().get(index));
		}
	}
	
	return properties;
}

public boolean checkOwnership(String token)
{
	Long user_Id = userService.getUserID(token);
	
	for(int index = 0;index <propertyRepository.findAll().size();index++ )
	{
		if(propertyRepository.findAll().get(index).getUser().getUser_Id() == user_Id )
		{
			return true;
		}
	}
	return false;
}

public List<Property> searchProperty(String title,int rooms,int visitors) throws Exception
{
	List<Property> properties = new ArrayList<>();
	
	if(propertyRepository.findAll().isEmpty() != true )
	{
		for(int index = 0; index < propertyRepository.findAll().size() ;index++)
		{
			if(propertyRepository.findAll().get(index).getCity().equals(title) || propertyRepository.findAll().get(index).getProp_name().equals(title)||propertyRepository.findAll().get(index).getCountry().equals(title) ||propertyRepository.findAll().get(index).getProvince().equals(title)||propertyRepository.findAll().get(index).getProp_type().equals(title) )
			{
			
				if(rooms < 1 && visitors < 1)
				{
				properties.add(propertyRepository.findAll().get(index));
				}
				else if(rooms <= propertyRepository.findAll().get(index).getNumberRoom() && visitors <= 						propertyRepository.findAll().get(index).getNumberRoom())
				{	
					properties.add(propertyRepository.findAll().get(index));
				}
		}
	}
	}
	else
	{
		throw new Exception("No results");
	}
	
	return properties;
	

}

public List<Property> displayProperty(String title)
{
	List<Property> properties = new ArrayList<>();
	
	for (int index=0;index <propertyRepository.findAll().size();index++) {
	
		if(propertyRepository.findAll().get(index).getProp_name().equalsIgnoreCase(title)||propertyRepository.findAll().get(index).getCity().equalsIgnoreCase(title)||propertyRepository.findAll().get(index).getCountry().equalsIgnoreCase(title)||propertyRepository.findAll().get(index).getProvince().equalsIgnoreCase(title))
		{
			properties.add(propertyRepository.findAll().get(index));
		}
		
	}
	
	return properties;
}

public double getAvarage(String title)
{
	 double total = 0;
	 int sum = 0;
	 if(propertyRepository.findAll().isEmpty() != true) {
	 for (int index=0;index <propertyRepository.findAll().size();index++) {
		 if(propertyRepository.findAll().get(index).getProp_name().equals(title)||propertyRepository.findAll().get(index).getCity().equals(title)||propertyRepository.findAll().get(index).getCountry().equals(title))
		 {
			 total = total + propertyRepository.findAll().get(index).getPrice();
			 sum++;
		 }
		 
	 }
	 }else
	 {
		 return 0;
	 }
	return Double.parseDouble(df.format(total / sum)); 
}
 
 public List<AccBooking> viewReservations(int property_Id,String token)
 {
	 Long user_Id = userService.getUserID(token);
		
	 
	 List<AccBooking> accBooking = new ArrayList<>();
	 
	 for(int index = 0; index < accBookingRepository.findAll().size();index++)
	 {
		 if(accBookingRepository.findAll().get(index).getProperty().getProp_Id()== property_Id &&
				 accBookingRepository.findAll().get(index).getProperty().getUser().getUser_Id() == user_Id	 )
		 {
			 accBooking.add(accBookingRepository.findAll().get(index));
		 }
	 }
	 
	 return accBooking;
 }
 
public int countProperties(String title)
 {
	 int total = 0;
	 if(propertyRepository.findAll().isEmpty() != true) {
	 for (int index=0;index <propertyRepository.findAll().size();index++) {
		 if(propertyRepository.findAll().get(index).getProp_name().equals(title)||propertyRepository.findAll().get(index).getCity().equals(title)||propertyRepository.findAll().get(index).getCountry().equals(title)||propertyRepository.findAll().get(index).getProvince().equals(title))
			{
			 
			 total++;
			 
			}
		 
	 }
	 }else
	 {
		 return 0;
	 }
	return total;
 }
 
 public List<String> getPossibleEntry(String title)
	{
		
		List<String> Country = new ArrayList<>();
		List<String> City = new ArrayList<>();
		List<String> Province = new ArrayList<>();
		List<String> Results = new ArrayList<>();
		List<String> Place = new ArrayList<>();
		String country = null;
		String city = null;
		String province = null;
		String place = null;
		if(propertyRepository.findAll().isEmpty())
		{
			return null;
		}else
		{
			for(int x = 0 ; x < propertyRepository.findAll().size(); x++)
			{
				for(int z = 0 ; z<title.length();z++)
				{
					if(propertyRepository.findAll().get(x).getCountry().startsWith(title)&&propertyRepository.findAll().get(x).getCountry().charAt(z) == title.charAt(z))
						{
							if(!Country.contains(propertyRepository.findAll().get(x).getCountry()))
							{
								country = propertyRepository.findAll().get(x).getCountry();
							}
								
						}
					
					if(propertyRepository.findAll().get(x).getCity().startsWith(title)&&propertyRepository.findAll().get(x).getCity().charAt(z) == title.charAt(z))
					{
						if(!City.contains(propertyRepository.findAll().get(x).getCity()))
						{
						city = propertyRepository.findAll().get(x).getCity();
						}
					}
					
					
					
					if(propertyRepository.findAll().get(x).getProvince().startsWith(title)&&propertyRepository.findAll().get(x).getProvince().charAt(z) == title.charAt(z))
					{
						if(!Province.contains(propertyRepository.findAll().get(x).getProvince()))
						{
						province = propertyRepository.findAll().get(x).getProvince();
						}
					}
					
					if(propertyRepository.findAll().get(x).getProp_name().startsWith(title)&&propertyRepository.findAll().get(x).getProp_name().charAt(z) == title.charAt(z))
					{
						if(!Place.contains(propertyRepository.findAll().get(x).getProp_name()))
						{
						place = propertyRepository.findAll().get(x).getProp_name();
						}	
					}
						
				}
				
					
			}
			
			Country.add(country);
			City.add(city);
			Province.add(province);
			Place.add(place);
			
		}
		Results.addAll(Country);
		Results.addAll(City);
		Results.addAll(Province);
		Results.addAll(Place);
		
		return Results;
	}
 
 
}

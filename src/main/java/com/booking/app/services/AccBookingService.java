package com.booking.app.services;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.app.modal.AccBooking;
import com.booking.app.repository.AccBookingRepository;
import com.booking.app.repository.PropertyRepository;
import com.booking.app.repository.PropertyTimeSheetRepository;
import com.booking.app.repository.UserRepository;


@Service
public class AccBookingService {

	@Autowired 
	private AccBookingRepository accBookingRepository;
	
	@Autowired 
	private UserRepository userRepository;
	
	@Autowired 
	private PropertyRepository propertyRepository;
		
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserPaymentMethodService userPaymentMethodService;
	
	@Autowired
	private PropertyTimeSheetRepository propertyTimeSheetRepository;
	
	
	public List<AccBooking> getAccBooking()
	{
		return accBookingRepository.findAll();
	}
	
	
	public Optional<AccBooking> getAccBooking(Long accBooking_Id)
	{
		return accBookingRepository.findById(accBooking_Id);
	}
	
	public boolean deleteAccBooking(Long accBooking_Id)
	{
		accBookingRepository.deleteById(accBooking_Id);
		return true;
	}

	public AccBooking createAccBooking(AccBooking accBooking)
	{
		return accBookingRepository.save(accBooking);
	}

	
	public AccBooking updateAccBooking(AccBooking accBooking)
	{
		return accBookingRepository.save(accBooking);
	}
	
	public AccBooking bookAccommodation(String token,Long property_id,int visitors,Date checkInDate, Date checkOutDate)
	{
		AccBooking accBooking = new AccBooking();
		Long user_id = userService.getUserID(token);
		
		boolean paymethod = userPaymentMethodService.confirmPaymentMethod(token);
		
		for(int index = 0; index < userRepository.findAll().size();index++ )
		{
			if(userRepository.findAll().get(index).getUser_Id() == user_id)
			{
				accBooking.setUser(userRepository.findAll().get(index));
			}
		}
		
		for(int index = 0; index < propertyRepository.findAll().size();index++)
		{
			if(propertyRepository.findAll().get(index).getProp_Id() == property_id)
			{
				accBooking.setProperty(propertyRepository.findAll().get(index));
			}
		}
		
		accBooking.setNoOfVisitors(visitors);
		accBooking.setCheckInDate(checkInDate);
		accBooking.setCheckOutDate(checkOutDate);
		int nights = checkOutDate.compareTo(checkInDate);
		
		accBooking.setNights(nights);
		
		if(paymethod == true)
		{
			this.deleteDate(checkInDate, checkOutDate, property_id);
			return accBookingRepository.save(accBooking);
		}
		
		return null;
	}
	
	public void deleteDate(Date startDate,Date endDate,Long property_id)
	{
		
		Calendar start = Calendar.getInstance();
		Calendar end= Calendar.getInstance();
		start.setTime(startDate);
		end.setTime(endDate);
		
		while(start.before(end))
		{
			start.add(Calendar.DAY_OF_MONTH, 1);
			for(int y = 0; y < propertyTimeSheetRepository.findAll().size();y++)
			{	
				if(start.equals(propertyTimeSheetRepository.findAll().get(y).getAvailableDates()) && property_id.equals(propertyTimeSheetRepository.findAll().get(y).getProperty().getProp_Id()))
				{
							propertyTimeSheetRepository.deleteById((long) y);;
				}
			}
		}
		
	}
	
	public List<AccBooking> viewBooking(String token)
	{
		Long user_id = userService.getUserID(token);
		
		List<AccBooking> accBooking = new ArrayList<>();
		
		for(int index = 0;index < accBookingRepository.findAll().size();index++)
		{
			if(accBookingRepository.findAll().get(index).getUser().getUser_Id() == user_id)
			{
				accBooking.add(accBookingRepository.findAll().get(index));
			}
		}
			
		return accBooking;
	}
	
	
}

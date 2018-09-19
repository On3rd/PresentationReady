package com.booking.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.app.modal.User;
import com.booking.app.modal.UserContactInformation;
import com.booking.app.repository.UserContactInformationRepository;
import com.booking.app.repository.UserRepository;

@Service
public class UserContactInformationService {


	@Autowired
	private UserContactInformationRepository userContactInformationRepository;
	
	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;
	
	public List<UserContactInformation> getUserContactInformation()
	{
		return userContactInformationRepository.findAll();
	}
	
	public Optional<UserContactInformation> getUserContactInformation(String token)
	{
		Long user_Id = userService.getUserID(token);
		User user = userRepository.getOne(user_Id);
		Long UserContactInformationId = null;
		
		for(int x = 0; x< userContactInformationRepository.findAll().size();x++)
		{
			if(userContactInformationRepository.findAll().get(x).getUser().equals(user))
			{
				UserContactInformationId = userContactInformationRepository.findAll().get(x).getUserContactInfo_Id();
			}
		}
		
		return userContactInformationRepository.findById(UserContactInformationId);
		
	}
	
	public boolean deleteUserContactInformation(String token)
	{
		Long user_Id = userService.getUserID(token);
		User user = userRepository.getOne(user_Id);
		
		for(int x = 0;x<userContactInformationRepository.findAll().size();x++)
		{
			if(userContactInformationRepository.findAll().get(x).getUser().equals(user))
			{
				userContactInformationRepository.deleteById(userContactInformationRepository.findAll().get(x).getUserContactInfo_Id());
				return true;
			}
		}
		return false;	
	}

	public UserContactInformation createUserContactInformation(UserContactInformation userContactInformation,String token)
	{
		Long user_Id = userService.getUserID(token);
		User user = userRepository.getOne(user_Id);
		userContactInformation.setUser(user);
		
		if(userContactInformationRepository.findAll() != null)
		{
			for(int x = 0; x <userContactInformationRepository.findAll().size();x++)
			{
				if(userContactInformationRepository.findAll().get(x).getUser().getUser_Id().equals(user_Id))
				{
					return null;
				}
			}
		}
		
		return userContactInformationRepository.save(userContactInformation);
	}

	public UserContactInformation updateUserContactInformation(UserContactInformation userContactInformation,String token)
	{
		Long user_Id = userService.getUserID(token);
		User user = userRepository.getOne(user_Id);
		UserContactInformation updatedContacts = new UserContactInformation();
		
		for(int x = 0; x< userContactInformationRepository.findAll().size();x++)
		{
			if(userContactInformationRepository.findAll().get(x).getUser().equals(user))
			{
				updatedContacts = userContactInformationRepository.findAll().get(x);
				updatedContacts.setAddressline2(userContactInformation.getAddressline2());
				updatedContacts.setCity(userContactInformation.getCity());
				updatedContacts.setCountry(userContactInformation.getCountry());
				updatedContacts.setPostalCode(userContactInformation.getPostalCode());
				updatedContacts.setProvince(userContactInformation.getProvince());
				updatedContacts.setStreetAddress(userContactInformation.getStreetAddress());
			}
		}
		
		return userContactInformationRepository.save(updatedContacts);
	}
	
		
}

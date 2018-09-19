package com.booking.app.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.app.modal.User;
import com.booking.app.modal.UserPaymentMethod;
import com.booking.app.repository.UserPaymentMethodRepository;
import com.booking.app.repository.UserRepository;

@Service
@Transactional
public class UserPaymentMethodService {
	
	
	@Autowired
	private UserPaymentMethodRepository userPaymentMethodRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	
	public List<UserPaymentMethod> getUserPaymentMethod()
	{
		return userPaymentMethodRepository.findAll();
	}
	

	public Optional<UserPaymentMethod> getUserPaymentMethod(String token)
	{
		Long user_Id = userService.getUserID(token);
		User user = userRepository.getOne(user_Id);
		Long userPaymentMethod_Id = null;
		
		if(userPaymentMethodRepository.findAll() != null)
		{
			for(int x = 0;x < userPaymentMethodRepository.findAll().size();x++)
			{
				if(userPaymentMethodRepository.findAll().get(x).getUser().equals(user))
				{
					userPaymentMethod_Id = userPaymentMethodRepository.findAll().get(x).getUserPaymentMethod_Id();
				}
				else
				{
					return null;
				}
			}
		}
		else
		{
			return null;
		}
		
		return userPaymentMethodRepository.findById(userPaymentMethod_Id);
	}
	
	
	public boolean deleteUserPaymentMethod(String token)
	{
		Long user_Id = userService.getUserID(token);
		User user = userRepository.getOne(user_Id);
		Long userPaymentMethodId = null;
		
		for(int x = 0;x<userPaymentMethodRepository.findAll().size();x++)
		{
			if(userPaymentMethodRepository.findAll().get(x).getUser().equals(user))
			{
				userPaymentMethodId = userPaymentMethodRepository.findAll().get(x).getUserPaymentMethod_Id();
				userPaymentMethodRepository.deleteById(userPaymentMethodId);
				return true;
			}
		}
		
		return false;
		
	}
	
	
	public boolean confirmPaymentMethod(String token)
	{
		Long user_id = userService.getUserID(token);

		for(int index = 0 ; index< userPaymentMethodRepository.findAll().size();index++)
		{
			if(userPaymentMethodRepository.findAll().get(index) != null && userPaymentMethodRepository.findAll().get(index).getUser().getUser_Id() == user_id)
			{
				 return true;	
			}
			
		}
		return false;
	}

	
	public UserPaymentMethod createUserPaymentMethod(UserPaymentMethod userPaymentMethod,String token)
	{
		Long user_id =userService.getUserID(token);
		User user =  userRepository.getOne(user_id);
		userPaymentMethod.setUser(user);
			
		if(userPaymentMethodRepository != null)
		{
			for (int x = 0 ;x < userPaymentMethodRepository.findAll().size();x++)
			{
				if(userPaymentMethodRepository.findAll().get(x).getUser().equals(user))
				{
				return null;
				}
				
			}
			return userPaymentMethodRepository.save(userPaymentMethod);
		}
		
		return null;
	}


	public UserPaymentMethod updateUserPaymentMethod(UserPaymentMethod userPaymentMethod,String token) throws Exception
	{
		Long user_Id = userService.getUserID(token);
		User user = userRepository.getOne(user_Id);
		UserPaymentMethod updatedPaymentMethod = new UserPaymentMethod();
		
		for(int x = 0 ; x<userPaymentMethodRepository.findAll().size();x++)
		{
			if(userPaymentMethodRepository.findAll().get(x).getUser().equals(user))
			{
				updatedPaymentMethod = userPaymentMethodRepository.findAll().get(x);
				updatedPaymentMethod.setCard_number(userPaymentMethod.getCard_number());
				updatedPaymentMethod.setCard_type(userPaymentMethod.getCard_type());
				updatedPaymentMethod.setExpiry_date(userPaymentMethod.getExpiry_date());
				updatedPaymentMethod.setSecurity_code(userPaymentMethod.getSecurity_code());
				return userPaymentMethodRepository.save(userPaymentMethod);
			}
			
		}
		
		
	return null;
	}
	
	 
}

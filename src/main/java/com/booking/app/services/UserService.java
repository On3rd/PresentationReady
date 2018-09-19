package com.booking.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.app.modal.User;
import com.booking.app.repository.UserRepository;
import com.booking.app.security.JwtGenerator;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service

public class UserService {

private String secret = "youtube";

@Autowired
private UserRepository userRepository;
	

@Autowired
private JwtGenerator jwtGenerator;


public String getRole(String token)
{
	String role = null;
	
	 try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
            role = (String) body.get("role");
            return role;
        }
        catch (Exception e) {
        	
            System.out.println(e);
            
        }
	 
	 return role;
}

public List<User> getUsers()
{
	return userRepository.findAll();
}


public Optional<User> getUser(Long user_Id)
{
	return userRepository.findById(user_Id);
}


public boolean deleteUser(String token) throws Exception
{
	Long user_Id = this.getUserID(token);
	
	if(user_Id == null)
	{
		throw new Exception("User doesn't exist");
	}
	else
	{
		userRepository.deleteById(user_Id);	
		return true;
	}
	
}

public boolean validateMail(String email)
{
	
		if(email.contains("@gmail.com") || email.contains("@yahoo.com") || email.contains("@hotmail.com"))
		{
			return true;
		}
		
	return false;
}

public boolean validatePhoneNumber(String phonenumber)
{
	if(phonenumber.length() == 10)
	{
		return true;
	}
	return false;
	
}

public User createUser(User user) throws Exception 
{
	if(userRepository.findAll().contains(user))
	{
		return null;
	}
	
	if(user.getRole() == null)
	{
		user.setRole("Customer");
	}
	
	for(int x = 0 ;x<userRepository.findAll().size();x++)
	{
		if(user.getEmail().equals(userRepository.findAll().get(x).getEmail()))
		{
			throw new Exception("Email already exists");
		}
	
	}
	if(this.validatePhoneNumber(user.getPhonenumber()))
	{
		if(this.validateMail(user.getEmail()))
		{
			return userRepository.save(user);
		}
		else
		{
			throw new Exception("Invalid mail");
		}
	}else
	{
		throw new Exception("Enter 10 digits");
	}
	
}


public User updateUser(User user,String token) throws Exception
{
	Long user_Id = this.getUserID(token);
	User updatedUser = userRepository.getOne(user_Id);
	if(updatedUser != null)
	{
	updatedUser.setName(user.getName());
	updatedUser.setSurname(user.getSurname());
	updatedUser.setPhonenumber(user.getPhonenumber());
	
	return userRepository.save(updatedUser);	
	}
	else
	{
		throw new Exception("Update Failed");
	}
		
}
	
	


public String login(String email ,String password) {
	

	User user = new User();
	
	for (int x = 0; x < userRepository.findAll().size(); x++) {
		
		if (userRepository.findAll().get(x).getEmail().equals(email)
				&& userRepository.findAll().get(x).getPassword().equals(password))
		{
			user = userRepository.findAll().get(x);
			return jwtGenerator.generate(user).toString();
		}
		
		
	}

	return null;

}

public String getUsername(String token)
{
	String username = null;
	
	 try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
            username = (String) body.get("username");
            return username;
        }
        catch (Exception e) {
            System.out.println(e);
        }
	 
	 return username;
}

public Long getUserID(String token)
{
	Long userId = null;
	
	try {
		Claims body = Jwts.parser()
	
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
     userId =  Long.parseLong((String) body.get("userId"));
          
     return userId;
	 }
    catch (Exception e) {
    	
        System.out.println(e);
        
    }
       
	return userId;
}

public User searchUser(Long user_id)
{
	for(int index = 0; index < userRepository.findAll().size();index++)
	{
		if(userRepository.findAll().get(index).getUser_Id().equals(user_id))
		{
			return userRepository.findAll().get(index);
		}
	}
	return null;
}
		
}

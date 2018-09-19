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

import com.booking.app.modal.User;
import com.booking.app.services.UserService;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins ="http://localhost:4200",allowedHeaders = "*")

public class UserController {

	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(path = "/users",method = RequestMethod.GET, produces = "application/json")
	public List<User> getUsers()
	{
		return userService.getUsers();
	}
	
	@RequestMapping(path = "/users/{user_Id}",method = RequestMethod.GET, produces = "application/json")
	public Optional<User> getUser(@PathVariable Long user_Id)
	{
		return userService.getUser(user_Id);
	}
	
	@RequestMapping(path = "/user/{token}",method = RequestMethod.DELETE, produces = "application/json")
	public boolean deleteUser(@PathVariable String token) throws Exception
	{
		return userService.deleteUser(token);
	}

	@RequestMapping(path = "/users",method = RequestMethod.POST, consumes = "application/json")
	public User createUser(@RequestBody User user) throws Exception
	{
		return userService.createUser(user);
	}

	@RequestMapping(path = "/updateProfile/{token}",method = RequestMethod.PUT, consumes = "application/json")
	public User updateUser(@RequestBody User user,@PathVariable String token) throws Exception
	{
		return userService.updateUser(user,token);
	}
	
	
	@RequestMapping(path = "/userlogin/{email}/{password}",method = RequestMethod.GET, produces = "application/json")
	public String login( @PathVariable String email , @PathVariable String password)
	{
		return userService.login(email, password);
	
	}
	
	@RequestMapping(path = "/username/{token}", method = RequestMethod.GET,produces = "text/plain")
	public String getUsername(@PathVariable String token)
	{
		 return userService.getUsername(token);
	}
	
	@RequestMapping(path = "/role/{token}", method = RequestMethod.GET,produces = "application/json")
	public String getRole(@PathVariable String token)
	{
		return userService.getRole(token);
	}
	
	@RequestMapping(path = "/getUserID/{token}", method = RequestMethod.GET,produces = "application/json")
	public Long getUserID(@PathVariable String token)
	{
		
		return userService.getUserID(token);
	}
	
	@RequestMapping(path = "/searchUser/{user_id}", method = RequestMethod.GET,produces = "application/json")
	public User searchUser(@PathVariable long user_id)
	{
		
		return userService.searchUser(user_id);
	}
	
	 

	
	
}

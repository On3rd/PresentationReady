package com.booking.app.modal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreType;


@Entity
@Table(name = "usertable")
@JsonIgnoreType
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long user_Id;
	
	private String name;
	private String surname;
	private String email;
	private String phonenumber;
	private String password;
	private String role;
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role.trim().toUpperCase();
	}
	public String getName() 
	{
		return name;
	}
	public Long getUser_Id() 
	{
		return user_Id;
	}
	public void setName(String name) 
	{
		this.name = name.toUpperCase().trim();
	}
	public String getSurname() 
	{
		return surname;
	}
	public void setSurname(String surname) 
	{
		this.surname = surname.toUpperCase().trim();
	}
	public String getEmail() 
	{
		return email;
	}
	public void setEmail(String email) 
	{
		this.email = email;
	}
	
	public String getPhonenumber() 
	{
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) 
	{
		this.phonenumber = phonenumber;
	}
	
	public String getPassword() 
	{
		return password;
	}
	public void setPassword(String password) 
	{
		this.password = password;
	}
	
	public User(String name, String surname, String email, String phonenumber, String password, String role) {
		
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.phonenumber = phonenumber;
		this.password = password;
		this.role = role;
	}
	public User()
	{
		
	}
	
}

package com.booking.app.modal;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

@Entity
@Table(name = "userPaymentMethod")
@JsonIgnoreType
public class UserPaymentMethod {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long userPaymentMethod_Id;
	
	@OneToOne
	private User user;
	
	private String card_type;
	private String card_number;
	private Date expiry_date;
	private String security_code;
	
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public String getCard_type() {
		return card_type;
	}
	public void setCard_type(String card_type) {
		this.card_type = card_type.toUpperCase().trim();
	}
	public String getCard_number() {
		return card_number;
	}
	public void setCard_number(String card_number) {
		this.card_number = card_number;
	}
	public Date getExpiry_date() {
		return expiry_date;
	}
	public void setExpiry_date(Date expiry_date) {
		this.expiry_date = expiry_date;
	}
	public String getSecurity_code() {
		return security_code;
	}
	public void setSecurity_code(String security_code) {
		this.security_code = security_code;
	}
	public Long getUserPaymentMethod_Id() {
		return userPaymentMethod_Id;
	}
	public UserPaymentMethod(User user, String card_type, String card_number, Date expiry_date,
			String security_code) {
		super();
		this.user = user;
		this.card_type = card_type;
		this.card_number = card_number;
		this.expiry_date = expiry_date;
		this.security_code = security_code;
	}
	public UserPaymentMethod() {
		
	}
	
	
	
}

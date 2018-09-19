package com.booking.app.modal;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

@Entity
@Table (name = "PropertyTimeSheet")
@JsonIgnoreType
public class PropertyTimeSheet {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY	)
	@Column(name= "propTimeSheetId")
	private Long propTimeSheetId;
	
	private Calendar availableDates;
	
	/*@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "prop_Id",referencedColumnName= "prop_Id")*/
	
	public Calendar getAvailableDates() {
		return availableDates;
	}

	public void setAvailableDates(Calendar availableDates) {
		this.availableDates = availableDates;
	}

	@OneToOne
	private Property property;
	
	
	public Long getPropTimeSheetId() {
		return propTimeSheetId;
	}

	public void setPropTimeSheetId(Long propTimeSheetId) {
		this.propTimeSheetId = propTimeSheetId;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	public Property getProperty() {
		return property;
	}

	public PropertyTimeSheet(Calendar availableDates, Property property) {
	
		this.availableDates = availableDates;
		this.property = property;
	}

	public PropertyTimeSheet() {
		
	}
	
	
	
}

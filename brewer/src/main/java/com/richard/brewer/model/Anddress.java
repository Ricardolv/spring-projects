package com.richard.brewer.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Embeddable
public class Anddress implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "address_street")
	private String street;
	
	@Column(name = "address_number")
	private String number;
	
	@Column(name = "address_complement")
	private String complement;
	
	@Column(name = "address_zipcode")
	private String zipCode;
	
	@ManyToOne
	@JoinColumn(name = "code_city")
	private City city;
	
	@Transient
	private State state;
	
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
	
	public String getNameCityInitialsState() {
		if (this.city != null) {
			return this.city.getName() + "/" + this.city.getState().getInitials();
		}
		
		return null;
	}
	
	
}

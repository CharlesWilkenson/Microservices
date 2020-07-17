package org.ClientService.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

@Entity
public class Adress implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
 private String adress_id;
 private String city;
 private String street;
 private int number;

 @JsonIgnore
 @OneToOne(mappedBy = "adress" )
 private Client client;
	








@JsonSetter
public void setClient(Client client) {
	this.client = client;
}




public Adress(String city, String street, int number) {
	super();
	this.city = city;
	this.street = street;
	this.number = number;
}




public String getAdress_id() {
	return adress_id;
}




public void setAdress_id(String adress_id) {
	this.adress_id = adress_id;
}






 
 public String getCity() {
	return city;
}



public Adress(String adress_id, String city, String street, int number) {
	super();
	this.adress_id = adress_id;
	this.city = city;
	this.street = street;
	this.number = number;
}



public Adress() {
	super();
	// TODO Auto-generated constructor stub
}

public void setCity(String city) {
	this.city = city;
}

public String getStreet() {
	return street;
}

public void setStreet(String street) {
	this.street = street;
}




public int getNumber() {
	return number;
}

public void setNumber(int number) {
	this.number = number;
}


	
	
}

package org.OperationService.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;



@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Account_Type",discriminatorType = DiscriminatorType.STRING,length = 2)

public abstract class Account implements Serializable{


/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
@Id
private String accountNumber;
private double balance;
private String currency;
private boolean active;
@Temporal(TemporalType.DATE)
private Date date;

private String client;

@OneToMany(mappedBy = "account")
@JsonIgnore
private Collection<Operation> operations;





public Account(String accountNumber, double balance, String currency, boolean active, Date date, String client) {
	super();
	this.accountNumber = accountNumber;
	this.balance = balance;
	this.currency = currency;
	this.active = active;
	this.date = date;
	this.client = client;
}


public Account(String accountNumber) {
	super();
	this.accountNumber = accountNumber;
}


public String getClient() {
	return client;
}


public void setClient(String client) {
	this.client = client;
}



@JsonSetter
public void setOperations(Collection<Operation> operations) {
	this.operations = operations;
}




public Account(String accountNumber, double balance, String currency, boolean active, Date date) {
	super();
	this.accountNumber = accountNumber;
	this.balance = balance;
	this.currency = currency;
	this.active = active;
	this.date = date;
}


public Date getDate() {
	return date;
}

public void setDate(Date date) {
	this.date = date;
}



public boolean isActive() {
	return active;
}

public void setActive(boolean active) {
	this.active = active;
}



public String getAccountNumber() {
	return accountNumber;
}

public void setAccountNumber(String accountNumber) {
	this.accountNumber = accountNumber;
}

public double getBalance() {
	return balance;
}

public void setBalance(double balance) {
	this.balance = balance;
}

public String getCurrency() {
	return currency;
}

public void setCurrency(String currency) {
	this.currency = currency;
}

public Account() {
	super();
	// TODO Auto-generated constructor stub
}








}

package org.OperationService.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "CA")
public class CheckingAccount extends Account{
	public CheckingAccount(String accountNumber, double balance, String currency, boolean active, Date date,
			String client) {
		super(accountNumber, balance, currency, active, date, client);
		// TODO Auto-generated constructor stub
	}






	public CheckingAccount(String accountNumber, double balance, String currency, boolean active, Date date) {
		super(accountNumber, balance, currency, active, date);
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1L;

	private double decouvert;

	public double getDecouvert() {
		return decouvert;
	}




	public CheckingAccount(String accountNumber, double balance, String currency, boolean active, Date date,
			double decouvert) {
		super(accountNumber, balance, currency, active, date);
		this.decouvert = decouvert;
	}




	public CheckingAccount(String accountNumber, double balance, String currency, boolean active, Date date,
			String client, double decouvert) {
		super(accountNumber, balance, currency, active, date, client);
		this.decouvert = decouvert;
	}




	public void setDecouvert(double decouvert) {
		this.decouvert = decouvert;
	}

	public CheckingAccount() {
		super();
		
	}
}

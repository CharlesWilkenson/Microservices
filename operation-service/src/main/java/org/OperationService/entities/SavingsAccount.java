package org.OperationService.entities;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "SA")
public class SavingsAccount extends Account{

	
	private static final long serialVersionUID = 1L;

	public SavingsAccount(String accountNumber, double balance, String currency, boolean active, Date date,
			String client, double rate) {
		super(accountNumber, balance, currency, active, date, client);
		this.rate = rate;
	}

public SavingsAccount(String accountNumber, double balance, String currency, boolean active, Date date,
			double rate) {
		super(accountNumber, balance, currency, active, date);
		this.rate = rate;
	}

public SavingsAccount(String accountNumber, double balance, String currency, boolean active, Date date, String client) {
	super(accountNumber, balance, currency, active, date, client);
	// TODO Auto-generated constructor stub
}

public SavingsAccount(double rate) {
	super();
	this.rate = rate;
}

public SavingsAccount(String accountNumber, double balance, String currency, boolean active, Date date) {
	super(accountNumber, balance, currency, active, date);
	// TODO Auto-generated constructor stub
}

private double rate;

public SavingsAccount() {
	super();
	
}

public double getRate() {
	return rate;
}


public void setRate(double rate) {
	this.rate = rate;
}
	
	
	
	
	
	
	
	
	
}

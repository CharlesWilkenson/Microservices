package org.OperationService.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "Deposit")
public class Deposit extends Operation{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Deposit(double amount, Account account, Date operationDate) {
		super(amount, account, operationDate);
		// TODO Auto-generated constructor stub
	}

	public Deposit() {
		super();
		// TODO Auto-generated constructor stub
	}




}

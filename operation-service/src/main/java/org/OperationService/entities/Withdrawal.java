package org.OperationService.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "Withdrawal")
public class Withdrawal extends Operation{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Withdrawal() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Withdrawal(double amount, Account account, Date operationDate) {
		super(amount, account, operationDate);
		// TODO Auto-generated constructor stub
	}



}

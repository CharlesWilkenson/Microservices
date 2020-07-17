package org.OperationService.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="Transfert")
public class Transfert extends Operation{


	public Transfert() {
		super();
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1L;

	public Transfert(double amount, Account account, Date operationDate) {
		super(amount, account, operationDate);
		// TODO Auto-generated constructor stub
	}

	private String beneficiaryNumber;


	public Transfert(double amount, Account account, Date operationDate, String beneficiaryNumber) {
		super(amount, account, operationDate);
		this.beneficiaryNumber = beneficiaryNumber;
	}

	public String getBeneficiaryNumber() {
		return beneficiaryNumber;
	}

	public void setBeneficiaryNumber(String beneficiaryNumber) {
		this.beneficiaryNumber = beneficiaryNumber;
	}

	
	
	
}

package org.OperationService.entities;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Type",discriminatorType =DiscriminatorType.STRING,length = 20)
public abstract class Operation implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	

	private double amount;
	
	@ManyToOne
	private Account account;
	public Operation(double amount, Account account, Date operationDate) {
		super();
		this.amount = amount;
		this.account = account;
		this.operationDate = operationDate;
	}


	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public double getAmount() {
		return amount;
	}



	public void setAmount(double amount) {
		this.amount = amount;
	}



	public Account getAccount() {
		return account;
	}



	public void setAccount(Account account) {
		this.account = account;
	}



	public Date getOperationDate() {
		return operationDate;
	}



	public void setOperationDate(Date operationDate) {
		this.operationDate = operationDate;
	}


	@Temporal(TemporalType.TIMESTAMP)
	private Date operationDate;
	
	
	
	public Operation() {
		super();
	
	}
	
	
	
	
	
	
	
}

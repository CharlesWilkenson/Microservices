package com.employeeservice.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

@Entity
public class Salary implements Serializable{

	private static final long serialVersionUID = 1L;
@Id
private String salary_id;
private double gross_salary;
private double net_salary;
public Salary(String salary_id) {
	super();
	this.salary_id = salary_id;
}

public Salary(double gross_salary, double net_salary, double taux) {
	super();
	this.gross_salary = gross_salary;
	this.net_salary = net_salary;
	this.taux = taux;
}

public Salary(String salary_id, double gross_salary, double net_salary, double taux) {
	super();
	this.salary_id = salary_id;
	this.gross_salary = gross_salary;
	this.net_salary = net_salary;
	this.taux = taux;
}

private double taux;

@JsonIgnore
@OneToOne(mappedBy = "salary")
private Employee employee ;




public String getSalary_id() {
	return salary_id;
}

public void setSalary_id(String salary_id) {
	this.salary_id = salary_id;
}

public void setTaux(double taux) {
	this.taux = taux;
}

public double getGross_salary() {
	return gross_salary;
}

public void setGross_salary(double gross_salary) {
	this.gross_salary = gross_salary;
}

public double getNet_salary() {
	return net_salary;
}

public void setNet_salary(double net_salary) {
	this.net_salary = net_salary;
}

public double getTaux() {
	return taux;
}

public void setTaux1(double taux) {
	this.taux = taux;
}



@JsonSetter
public void setEmployee(Employee employee) {
	this.employee = employee;
}

public Salary() {
	super();
	// TODO Auto-generated constructor stub
}


}
package com.employeeservice.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(	name = "employee", 
uniqueConstraints = { 
	@UniqueConstraint(columnNames = "email") 
})
public class Employee implements Serializable{

	public Employee(String lastname, String firstname) {
		super();
		this.lastname = lastname;
		this.firstname = firstname;
	}

	private static final long serialVersionUID = 1L;

 @Id
 private String code;
 private String lastname;
 private String firstname;
 private String sex;  
 private String dateBirth;
 private String placeBirth;
 private String citizenship;
 private String identification;
 private String maritalStatus;
 private String phone;
 private String email;
 private String fonction;
 private String department;
 private String type;
 @Lob
 private byte[]photo;
 
 @OneToOne(fetch = FetchType.EAGER)
 private Adress adress;
 @Temporal(TemporalType.DATE)
 private Date date;
 
 @OneToOne(fetch = FetchType.EAGER)
 //@Column(name = "salary",nullable = true)
 private Salary salary;
 
 public Employee(String code, String lastname, String firstname, String sex, String dateBirth, String placeBirth,
		String citizenship, String identification, String maritalStatus, String phone, String email, String fonction,
		String department, String type, Adress adress) {
	super();
	this.code = code;
	this.lastname = lastname;
	this.firstname = firstname;
	this.sex = sex;
	this.dateBirth = dateBirth;
	this.placeBirth = placeBirth;
	this.citizenship = citizenship;
	this.identification = identification;
	this.maritalStatus = maritalStatus;
	this.phone = phone;
	this.email = email;
	this.fonction = fonction;
	this.department = department;
	this.type = type;
	this.adress = adress;
}


public Employee(String code, String lastname, String firstname, String sex, String dateBirth, String placeBirth,
		String citizenship, String identification, String maritalStatus, String phone, String email, String fonction,
		String department, String type, Adress adress, Date date, Salary salary) {
	super();
	this.code = code;
	this.lastname = lastname;
	this.firstname = firstname;
	this.sex = sex;
	this.dateBirth = dateBirth;
	this.placeBirth = placeBirth;
	this.citizenship = citizenship;
	this.identification = identification;
	this.maritalStatus = maritalStatus;
	this.phone = phone;
	this.email = email;
	this.fonction = fonction;
	this.department = department;
	this.type = type;
	this.adress = adress;
	this.date = date;
	this.salary = salary;
}


public Employee(String code, String lastname, String firstname, String sex, String dateBirth, String placeBirth,
		String citizenship, String identification, String maritalStatus, String phone, String email, String fonction,
		String department, String type, byte[] photo, Adress adress,Salary salary) {
	super();
	this.code = code;
	this.lastname = lastname;
	this.firstname = firstname;
	this.sex = sex;
	this.dateBirth = dateBirth;
	this.placeBirth = placeBirth;
	this.citizenship = citizenship;
	this.identification = identification;
	this.maritalStatus = maritalStatus;
	this.phone = phone;
	this.email = email;
	this.fonction = fonction;
	this.department = department;
	this.type = type;
	this.photo = photo;
	this.adress = adress;
	this.salary=salary;
}

public Salary getSalary() {
	return salary;
}

public void setSalary(Salary salary) {
	this.salary = salary;
}

public byte[] getPhoto() {
	return photo;
}

public void setPhoto(byte[] photo) {
	this.photo = photo;
}

public Date getDate() {
	return date;
}

public void setDate(Date date) {
	this.date = date;
}

public Employee() {
	super();
	// TODO Auto-generated constructor stub
}

public String getCode() {
	return code;
}

public void setCode(String code) {
	this.code = code;
}

public String getLastname() {
	return lastname;
}

public void setLastname(String lastname) {
	this.lastname = lastname;
}

public String getFirstname() {
	return firstname;
}

public void setFirstname(String firstname) {
	this.firstname = firstname;
}

public String getSex() {
	return sex;
}

public void setSex(String sex) {
	this.sex = sex;
}

public String getDateBirth() {
	return dateBirth;
}

public void setDateBirth(String dateBirth) {
	this.dateBirth = dateBirth;
}

public String getPlaceBirth() {
	return placeBirth;
}

public void setPlaceBirth(String placeBirth) {
	this.placeBirth = placeBirth;
}

public String getCitizenship() {
	return citizenship;
}

public void setCitizenship(String citizenship) {
	this.citizenship = citizenship;
}

public String getIdentification() {
	return identification;
}

public void setIdentification(String identification) {
	this.identification = identification;
}

public String getMaritalStatus() {
	return maritalStatus;
}

public void setMaritalStatus(String maritalStatus) {
	this.maritalStatus = maritalStatus;
}

public String getPhone() {
	return phone;
}

public void setPhone(String phone) {
	this.phone = phone;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getFonction() {
	return fonction;
}

public void setFonction(String fonction) {
	this.fonction = fonction;
}

public String getDepartment() {
	return department;
}

public void setDepartment(String department) {
	this.department = department;
}

public String getType() {
	return type;
}

public void setType(String type) {
	this.type = type;
}

public Adress getAdress() {
	return adress;
}

public Employee(String code) {
	super();
	this.code = code;
}

public Employee(String code, String lastname, String firstname, String sex, String dateBirth, String placeBirth,
		String citizenship, String identification, String maritalStatus, String phone, String email, String fonction,
		String department, String type,Adress adress,Salary salary) {
	super();
	this.code = code;
	this.lastname = lastname;
	this.firstname = firstname;
	this.sex = sex;
	this.dateBirth = dateBirth;
	this.placeBirth = placeBirth;
	this.citizenship = citizenship;
	this.identification = identification;
	this.maritalStatus = maritalStatus;
	this.phone = phone;
	this.email = email;
	this.fonction = fonction;
	this.department = department;
	this.type = type;
	this.adress = adress;
	this.salary=salary;
}

public void setAdress(Adress adress) {
	this.adress = adress;
}


 
 
 
 
}

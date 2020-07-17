package org.ClientService.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class Client implements Serializable{
	private static final long serialVersionUID = 1L;


	     @Id
	     private String code;
	     public Client(String code) {
			super();
			this.code = code;
		}

		public Client(String lastname, String firstname) {
			super();
			this.lastname = lastname;
			this.firstname = firstname;
		}

		private String lastname;
		 private String firstname;
		 private String sex;                         
		 private String dateBirth;
		 private String citizenship;
		 private String identification;
		 private String phone;
		 private String email;
	 

	 private String  account;
	 
	 @OneToOne
	 private Adress adress;


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}


	public Client() {
		super();
		// TODO Auto-generated constructor stub
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


	public Adress getAdress() {
		return adress;
	}

	public void setAdress(Adress adress) {
		this.adress = adress;
	}

	public Client(String code, String lastname, String firstname, String sex, String dateBirth, String citizenship,
			String identification, String phone, String email, Adress adress) {
		super();
		this.code = code;
		this.lastname = lastname;
		this.firstname = firstname;
		this.sex = sex;
		this.dateBirth = dateBirth;
		this.citizenship = citizenship;
		this.identification = identification;
		this.phone = phone;
		this.email = email;
		this.adress = adress;
	}




 

}

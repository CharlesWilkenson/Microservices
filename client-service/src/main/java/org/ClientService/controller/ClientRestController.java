package org.ClientService.controller;


import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Random;
import org.ClientService.entities.Adress;
import org.ClientService.entities.Client;
import org.ClientService.services.Services;
/*import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;*/
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.client.loadbalancer.LoadBalanced;

import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpHeaders;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@EnableOAuth2Client
@Configuration
public class ClientRestController {
	@Autowired
	private Services dao;
	/*
	 * @Autowired private RestTemplate restTemplate;
	 */
	@Autowired
	private OAuth2RestTemplate oAuth2RestTemplate ;
	


	/*
	 * @RequestMapping("/user") public Principal user(Principal user) { return user;
	 * }
	 */

//@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping(value = "/saveClient")
	public ResponseEntity<Object> saveClient(@RequestParam(name = "lastname") String lastname,
			@RequestParam(name = "firstname") String firstname, @RequestParam(name = "sex") String sex,
			@RequestParam(name = "email") String email, @RequestParam(name = "phone") String phone,
			@RequestParam(name = "dateBirth") String dateBirth,
			@RequestParam(name = "identification") String identification,
			@RequestParam(name = "citizenship") String citizenship, @RequestParam(name = "street") String street,
			@RequestParam(name = "city") String city, @RequestParam(name = "number", defaultValue = "0") int number,
			@RequestParam(name = "currency") String currency,
			@RequestParam(name = "balance", defaultValue = "1") double balance,
			@RequestParam(name = "rate", defaultValue = "1") String rate,
			@RequestParam(name = "decouvert", defaultValue = "1") double decouvert,
			@RequestParam(name = "accountType") String type) {
		// @JsonIgnoreProperties(ignoreUnknown = true)
		String code = code();
		MultiValueMap<String, Object> mapUser = new LinkedMultiValueMap<String, Object>();

		// Data attached to the request.
		mapUser.add("email", email);
		mapUser.add("role", "CLIENT");

		// Data attached to the request.
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("currency", currency);
		map.add("rate", rate);
		map.add("balance", balance);
		map.add("decouvert", decouvert);
		map.add("rate", rate);
		map.add("type", type);
		map.add("codeClient", code);


		Adress adress = new Adress("AD" + code, city, street, number);
		Client client = new Client(code, lastname, firstname, sex, dateBirth, citizenship, identification, phone, email,
				adress);

		try {
	
			// URL with URI-variable
			 String URL_ADD_ACCOUNT = "http://operation-service/addAccount";
			 String URL_ADD_USER = "http://user-service/createUser";
			  
			  ResponseEntity<String> resultUser =oAuth2RestTemplate.postForEntity(URL_ADD_USER, mapUser, String.class);
		      ResponseEntity<String> resultAccount = oAuth2RestTemplate.postForEntity(URL_ADD_ACCOUNT,map,  String.class);
		
			 
			 System.out.println("Type " + map.getFirst("type") + " balance " + map.getFirst("balance") + " Rate "
					+ map.getFirst("rate") + " Decouvert " + map.getFirst("decouvert"));
			System.out.println("Lastname " + lastname + " firstname " + firstname + " sexe " + sex + " email " + email);

		
			// @JsonIgnoreProperties
		  System.out.println("Code Client: "+code);
			// check response
			if (
				  resultAccount.getStatusCode() == HttpStatus.CREATED &&
				   resultUser.getStatusCode() == HttpStatus.CREATED ) {
				

				dao.saveClient(client, adress);
				System.out.println(
						"Request Successful: " +  resultAccount.getStatusCode()+"&&"+  resultUser.getStatusCode());
			} else {
				System.out.println("Request Failed: " +resultAccount.getStatusCode()+"&&"+  resultUser.getStatusCode());
			}

		} catch (final HttpClientErrorException e) {
			  System.out.println(e.getStatusCode());
			    System.out.println(e.getResponseBodyAsString());
			System.out.println("Erreur " + e.getMessage());
			return new ResponseEntity<Object>(new Client(), HttpStatus.INTERNAL_SERVER_ERROR);
			
		}

		return new ResponseEntity<Object>(client.getCode(), HttpStatus.CREATED);
	}


	

	@PutMapping(value = "/updateClient")
	public ResponseEntity<Object> updateClient(@RequestParam(name = "code") String code,
			@RequestParam(name = "adress_id") String adress_id, @RequestParam(name = "lastname") String lastname,
			@RequestParam(name = "firstname") String firstname, @RequestParam(name = "sex") String sex,
			@RequestParam(name = "email") String email, @RequestParam(name = "phone") String phone,
			@RequestParam(name = "dateBirth") String dateBirth,
			@RequestParam(name = "identification") String identification,
			@RequestParam(name = "citizenship") String citizenship, @RequestParam(name = "street") String street,
			@RequestParam(name = "city") String city, @RequestParam(name = "number") int number) {

		Adress adress = new Adress(adress_id, city, street, number);
		Client client = new Client(code, lastname, firstname, sex, dateBirth, citizenship, identification, phone, email,
				adress);

		try {

			dao.updateClient(client, adress);
		} catch (Exception e) {

			return new ResponseEntity<Object>(new Client(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return ResponseEntity.ok(HttpStatus.CREATED);

	}

	//@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	@PostMapping(value = "/findClient")
	public ResponseEntity<Object> searchClient(@RequestParam(name = "codeSearch") String code) {
		Client client = null;
		System.out.println("OK");
		try {
			client = dao.findClient(code);
	
			System.out.println("Lastname " + client.getLastname());

		} catch (Exception e) {
			System.out.println("Error " + e);
		}
		System.out.println("Adress " + client.getAdress().getCity());
		return new ResponseEntity<Object>(client, HttpStatus.OK);
	}

	@GetMapping(value = "/getClient/{code}")
	public ResponseEntity<Object> getClient(@PathVariable(name = "code") String code) {
		Client client = null;
		System.out.println("OK");
		try {
			client = dao.findClient(code);
	
		
		} catch (Exception e) {
			System.out.println("Error " + e);
		}
		System.out.println("Adress " + client.getAdress().getCity());
		return new ResponseEntity<Object>(client, HttpStatus.OK);
	}

//@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping(value = "/allClients")
	public ResponseEntity<?> allClients() {

		List<Client> clients = new ArrayList<Client>();
		try {
			clients = dao.findAllClients();
		

		} catch (Exception e) {

		}

		return new ResponseEntity<>(clients, HttpStatus.OK);
	}

	private String code() {
		Random rd = new Random();
		String code = String.format("%s%s%s", rd.nextInt(999), rd.nextInt(999), rd.nextInt(99));
		return code;
	}

	/*
	 * @GetMapping(value = "/allSavings") public SavingsAccount[] deposits() {
	 * String url = "http://operation-service/allSavings/"; SavingsAccount[]
	 * response = oAuth2RestTemplate.getForObject(url, SavingsAccount[].class);
	 * 
	 * for (SavingsAccount d : response) { System.out.println("Amount of money :" +
	 * d.balance); }
	 * 
	 * return response; }
	 * 
	 * @GetMapping(value = "/allChekings") public CheckingsAccount[] withdrawals() {
	 * String url = "http://localhost:9999/operation-service/allChekings/";
	 * 
	 * CheckingsAccount[] response = oAuth2RestTemplate.getForObject(url,
	 * CheckingsAccount[].class);
	 * 
	 * for (CheckingsAccount d : response) { System.out.println("Amount of money :"
	 * + d.getBalance()); }
	 * 
	 * return response; }
	 * 
	 * @GetMapping(value = "/allAccounts") public Account[] transferts() { String
	 * url = "http://operation-service/allAccounts/";
	 * 
	 * Account[] response = oAuth2RestTemplate.getForObject(url, Account[].class);
	 * 
	 * for (Account d : response) { System.out.println("Amount of money :" +
	 * d.getBalance()); }
	 * 
	 * return response; }
	 */

	@GetMapping(value = "/allclients")
	public ResponseEntity<Object> allclients() {

		List<Client> clients=dao.findAllClients();

		return new ResponseEntity<Object>(clients,HttpStatus.OK);
	}


}

                               


class Account {
	String accountNumber;
	double balance;
	String currency;
	boolean active;
	Date date;
	String client;

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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

}

class SavingsAccount {
	String accountNumber;
	double balance;
	String currency;
	boolean active;
	Date date;
	String client;
	double rate;

	public SavingsAccount(int number, double balance2, String currency2, boolean b, Date date2, String code,
			double rate2) {
		// TODO Auto-generated constructor stub
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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

}

class CheckingsAccount {
	public CheckingsAccount(int number, double balance2, String currency2, boolean b, Date date2, String code,
			double decouvert) {
		// TODO Auto-generated constructor stub
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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	String accountNumber;
	double balance;
	String currency;
	boolean active;
	Date date;
}

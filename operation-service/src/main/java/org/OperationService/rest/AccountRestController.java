package org.OperationService.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.websocket.server.PathParam;

import org.OperationService.entities.Account;
import org.OperationService.entities.CheckingAccount;
import org.OperationService.entities.SavingsAccount;
import org.OperationService.services.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountRestController {
	@Autowired
	private Services dao;

	@PostMapping(value = "/findAccount")
	public ResponseEntity<Object> findAccount(
			@RequestParam(name = "account_number",required = false) String code,
			@RequestParam(name = "accountTransfert",required = false) String codeT) {
		SavingsAccount save = null;
		CheckingAccount check = null;
		System.out.println("Methode is called ");
		Account ac=null;
		if(code==null) {
			 ac = dao.findAccount(codeT);	
		}else {
			
			 ac = dao.findAccount(code);
		}
		
		
		
		System.out.println("Search Account is called " + code);

		Account a = new Account() {
			private static final long serialVersionUID = 1L;
		};

		/*
		 * a.setAccountNumber(ac.getAccountNumber()); a.setActive(ac.isActive());
		 * a.setDate(ac.getDate()); a.setBalance(ac.getBalance());
		 * a.setCurrency(ac.getCurrency());
		 */

		try {
			
			// ac=dao.findAccount(code);
			 System.out.println("Balance "+a.getBalance());
			System.out.println("Currency "+a.getCurrency());

			/*
			 * if(a instanceof SavingsAccount) { SavingsAccount s=dao.findASaving(code);
			 * save=new SavingsAccount(s.getAccountNumber(), s.getBalance(),
			 * s.getCurrency(), s.isActive(), s.getDate(), s.getClient(), s.getRate());
			 * System.out.println("Balance save "+save.getBalance()); return new
			 * ResponseEntity<Object>(save,HttpStatus.OK); } else if(a instanceof
			 * CheckingAccount){
			 * 
			 * CheckingAccount c=dao.findCheking(code); check=new
			 * CheckingAccount(c.getAccountNumber(), c.getBalance(), c.getCurrency(),
			 * c.isActive(), c.getDate(), c.getClient(), c.getDecouvert());
			 * System.out.println("Balance check "+check.getBalance()); return new
			 * ResponseEntity<Object>(check,HttpStatus.OK); } else {
			 * System.out.println("No instance"); }
			 */
		} catch (Exception e) {
			System.out.println("error " + e.getMessage());
		}
		return new ResponseEntity<Object>(ac, HttpStatus.OK);
	}

	@GetMapping(value = "/allAccounts")
	public ResponseEntity<Object> allaAccounts() {

		List<Account> accounts = new ArrayList<Account>();
		Account a = new Account() {

			private static final long serialVersionUID = 1L;
		};

		try {
			accounts = dao.allAccounts();
	

		} catch (Exception e) {

		}
		return new ResponseEntity<>(accounts, HttpStatus.OK);
	}

	@GetMapping(value = "/allSavings")
	public ResponseEntity<Object> allaSavings() {

		List<SavingsAccount> savings = new ArrayList<SavingsAccount>();
		try {
			savings = dao.allSavings();
		
		} catch (Exception e) {

		}
		return new ResponseEntity<>(savings, HttpStatus.OK);
	}

	@GetMapping(value = "/allChekings")
	public ResponseEntity<Object> allChekings() {

		List<CheckingAccount> checkings = new ArrayList<CheckingAccount>();
		try {
			checkings = dao.allCheckings();
		
		} catch (Exception e) {

		}
		return new ResponseEntity<>(checkings, HttpStatus.OK);
	}

	@GetMapping(value = "/enable/{code}")
	public ResponseEntity<Object> enable(@PathVariable("code") String code) {
		dao.reactiverAccount(code);
		;
		try {

		} catch (Exception e) {

		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(value = "/disable/{code}")
	public ResponseEntity<Object> disable(@PathVariable("code") String code) {

		System.out.println("MEthod disable is called");
		dao.desactiverAccount(code);
		;
		try {

		} catch (Exception e) {

		}
		return new ResponseEntity<>(HttpStatus.OK);
	}


	@PostMapping(value = "/addAccount")
	public ResponseEntity<Object> addAccount(
			@RequestParam(name = "type") String type,
			@RequestParam(name = "codeClient") String codeClient, 
			@RequestParam(name = "currency") String currency,
			@RequestParam(name = "balance",defaultValue = "1")double balance, 
			@RequestParam(name = "rate",defaultValue = "1")double rate,
			@RequestParam(name = "decouvert",defaultValue = "1")double decouvert) {

		
		System.out.println("Service Operation has been consulted");
		try {
		//	String number = accountNumber();
			//System.out.println("accountNumber " + number);

			System.out.println("codeClient " + codeClient);


			/*
			 * System.out.println("Balance " + checking.getBalance());
			 * System.out.println("Balance " + saving.getBalance());
			 */

			if (type.equals("saving")) {
				  Account saving=new SavingsAccount(codeClient, balance, currency, true, new Date (),
						  codeClient, rate);
				 dao.addAccount(saving);
				System.out.println("Rate ");
			} else if (type.equals("checking")) {
				 Account checking=new CheckingAccount(codeClient, balance, currency, true, new Date
						  (), codeClient, decouvert );
				 dao.addAccount(checking);

				System.out.println("Decouvert ");
			}

		} catch (Exception e) {
			System.out.println("Error " + e.getMessage());
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}

	private String accountNumber() {

		Random rd = new Random();
		String code = String.format("%s%s%s", rd.nextInt(999), rd.nextInt(999), rd.nextInt(999));
		return code;
	}

}

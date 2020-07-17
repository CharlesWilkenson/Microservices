package org.OperationService.rest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.OperationService.entities.Account;
import org.OperationService.entities.Deposit;
import org.OperationService.entities.Transfert;
import org.OperationService.entities.Withdrawal;
import org.OperationService.services.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.client.util.Resources;

@RestController
public class OperationRest {
	@Autowired
	private Services dao;


	@PostMapping(value = "/savedeposit")
	public ResponseEntity<Object> saveDeposit(
			@RequestParam(name = "accountDeposit") String accountNumber,
			@RequestParam(name = "amount") double amount) {

		System.out.println("Method is called");
		
		dao.deposit(accountNumber,amount,  new Date());
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}

	@PostMapping(value = "/savetransfert")
	public ResponseEntity<Object> saveTransfert(
			@RequestParam(name = "accountTransfert") String accountNumber,
			@RequestParam(name = "amount") double amount,
			@RequestParam(value = "beneficiary_number") String beneficiaryNumber) {
	     	dao.transfert(accountNumber,beneficiaryNumber, amount ,new Date());
		System.out.println("Transfert");
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	

	@PostMapping(value = "/savewithdrawal")
	public ResponseEntity<Object> savewithdrawal(
			@RequestParam(name = "accountWithdraw") String accountNumber,
			@RequestParam(name = "amount") double amount) {
		dao.Withdrawal(accountNumber,amount,  new Date());
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}

	@GetMapping(value = "/alltransferts")
	public ResponseEntity<Object> allTransfert() {
		List<Transfert> transferts = dao.allTransferts();

	

		return new ResponseEntity<Object>(transferts, HttpStatus.OK);
	}

	@GetMapping(value = "/allwithdrawals")
	public ResponseEntity<Object> allWithdrawals() {

		List<Withdrawal> withdrawals = dao.allWithdrals();
	
		return new ResponseEntity<Object>(withdrawals, HttpStatus.OK);
	}

	
	@GetMapping(value = "/alldeposits")
	public ResponseEntity<Object> allDeposits() {
		List<Deposit> deposits = dao.allDeposits();
	

		return new ResponseEntity<Object>(deposits, HttpStatus.OK);
	}

	
	
	
	@PostMapping(value = "/verifyAccount")
	public ResponseEntity<Object> verifyAccount(@RequestParam(name = "account_number") String account_number) {
		System.out.println("Code " + account_number);		
 
		Account acc=dao.findAccount(account_number);		    
		  return new ResponseEntity<Object>(acc,HttpStatus.OK);

	}
	

	
	public OperationRest() {
		super();
		// TODO Auto-generated constructor stub
	}

	@GetMapping(value = "/alltransferts/{accountNumber}")
	public ResponseEntity<Object> allTransfertByNumber(@PathVariable(name = "accountNumber")String accountNumber) {

		List<Transfert> transferts = dao.allTransferts(accountNumber);
	
		return new ResponseEntity<Object>(transferts, HttpStatus.OK);
	}

	
	
	@GetMapping(value = "/allwithdrawals/{accountNumber}")
	public ResponseEntity<Object> allWithdrawalsByNumber(@PathVariable(name = "accountNumber")String accountNumber) {
	
		List<Withdrawal>  withdrawals = dao.allWithdrals(accountNumber);
	
		return new ResponseEntity<Object>(withdrawals, HttpStatus.OK);
	}

	
	
	@GetMapping(value = "/alldeposits/{accountNumber}")
	public List<Deposit> allDepositsByNumber(@PathVariable(name = "accountNumber")String accountNumber) {
		System.out.println("accountNumber Operation :"+accountNumber);
		List<Deposit> deposits = dao.allDeposits(accountNumber);
	
		return deposits;
	}
  
	
	
	private String accountNumber() {
		   Random rd=new Random();
		   String code=String.format("%s%s%s",rd.nextInt(999),rd.nextInt(999),rd.nextInt(999));
		   return code;
	}



}

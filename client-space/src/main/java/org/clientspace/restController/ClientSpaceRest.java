package org.clientspace.restController;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@RestController
public class ClientSpaceRest {

	
@Autowired
private OAuth2RestTemplate restTemplate;
	
	@PostMapping(value = "/verifyMyAccount")
	public ResponseEntity<Object> checkMyAccount(Model model,@RequestParam("account_number")String myaccountNumber){
		MultiValueMap<Object, String>map=new LinkedMultiValueMap<Object, String>();
		map.add("account_number", myaccountNumber);
		String URL_ACCOUNT="http://operation-service/findAccount";  
		ResponseEntity<Account> response = null;
		try {
			response = restTemplate.postForEntity(URL_ACCOUNT, map, Account.class);
		} catch (RestClientException e) {
			
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(response,HttpStatus.OK);
                               }

	
	@PostMapping(value = "/verifyHisAccount")
	public ResponseEntity<Object> checkHisAccount(Model model,@RequestParam("account_number2")String myaccountNumber){
		MultiValueMap<Object, String>map=new LinkedMultiValueMap<Object, String>();
		map.add("account_number", myaccountNumber);
		String URL_ACCOUNT="http://operation-service/findAccount";  
		ResponseEntity<Account> response = null;
		try {
			response = restTemplate.postForEntity(URL_ACCOUNT, map, Account.class);
		} catch (RestClientException e) {
			
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(response,HttpStatus.OK);
                               }

	
	
	
	
	@PostMapping(value = "/savetransfert")
	public ResponseEntity<Object> saveTransfert(
			@RequestParam(name = "account_number") String myaccountNumber,
			@RequestParam(name = "amount") double amount,
			@RequestParam(value = "account_number2") String beneficiaryNumber) {
		
		MultiValueMap<Object, String>map=new LinkedMultiValueMap<Object, String>();
		map.add("account_number", myaccountNumber);
		map.add("beneficiary_number", beneficiaryNumber);
		map.add("amount", ""+amount);
				
		String URL_TRANSFERT="http://operation-service/savetransfert";  
		try {
			ResponseEntity<Account> response = restTemplate.postForEntity(URL_TRANSFERT, map, Account.class);
		} catch (RestClientException e) {
			
			e.printStackTrace();
		}
	     
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
}


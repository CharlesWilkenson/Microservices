package org.OperationService.rest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.OperationService.entities.Account;
import org.OperationService.entities.CheckingAccount;
import org.OperationService.entities.Deposit;
import org.OperationService.entities.SavingsAccount;
import org.OperationService.entities.Transfert;
import org.OperationService.entities.Withdrawal;
import org.OperationService.services.Services;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class indexController {
	private String code;
	@Autowired
	private OAuth2RestTemplate oAuth2RestTemplate;
    private byte[]photo;
	@Autowired
	private Services dao;
	private String lastname;
	private String firstname;
	private String email;
	
	
	  @RequestMapping(value = "/saveForm") 
	  public String index(Model model) {
		  employeeInfo(model);
		/*
		 * model.addAttribute("lastname", lastname);
		 * model.addAttribute("firstname",firstname); model.addAttribute("email",
		 * email);
		 */
	  return"saveOperation"; }
	  
	  
	  @RequestMapping(value = "/alldepositsForm") 
	  public String alldeposits(Model model) { 
		  List<Deposit> list2 = dao.allDeposits();
		  model.addAttribute("deposits", list2);
		  employeeInfo(model);
		/*
		 *  model.addAttribute("lastname",
		 * lastname); model.addAttribute("firstname", firstname);
		 * model.addAttribute("email", email);
		 */ 
	  
	  return"alldeposits"; }
	  
	  
	  @RequestMapping(value = "/alltransfertsForm") public String
	  alltransferts(Model model) {
	  List<Transfert>allTransferts=dao.allTransferts();
	  model.addAttribute("transferts", allTransferts);
	  employeeInfo(model);
		/*
		 * model.addAttribute("lastname", lastname); model.addAttribute("firstname",
		 * firstname); model.addAttribute("email", email);
		 */ 
	  
	  return"alltransferts"; }
	  
	  @RequestMapping(value = "/allwithdrawalsForm") public String
	  allwithdrawals(Model model) { 
		  List<Withdrawal>allWith=dao.allWithdrals();
	  model.addAttribute("withdrawals", allWith);
	  employeeInfo(model);
		/*
		 * model.addAttribute("lastname", lastname); model.addAttribute("firstname",
		 * firstname); model.addAttribute("email", email);
		 */
	  
	  return"allwithdrawals"; }
	  
	  
	  
	  @RequestMapping(value = "/addAccountForm") 
	  public String addAccountForm(Model model) {
		  employeeInfo(model);
		/*
		 * model.addAttribute("lastname", lastname); model.addAttribute("firstname",
		 * firstname); model.addAttribute("email",email);
		 */
	  
	  return"addAccount"; }
	  
	  
	  @RequestMapping(value = "/searchAccountForm") public String
	  searchAccountForm(Model model) {
		/*
		 * model.addAttribute("lastname", lastname); model.addAttribute("firstname",
		 * firstname); model.addAttribute("email",email);
		 */ 
		  employeeInfo(model);
	  return"searchAccount"; }
	  
	  @RequestMapping(value = "/allAccountsForm") public String
	  allAccountsForm(Model model) {
		  List<Account> accounts=dao.allAccounts();
	  model.addAttribute("accounts", accounts);
	  employeeInfo(model);
		/*
		 * model.addAttribute("lastname", lastname); model.addAttribute("firstname",
		 * firstname); model.addAttribute("email", email);
		 */
	  return"allAccounts"; }
	  
	  @RequestMapping(value = "/allSavingAccountsForm") public String
	  allSavingAccountsForm(Model model) {
	  List<SavingsAccount>savings=dao.allSavings();
	  model.addAttribute("savings",savings); 
		/*
		 * model.addAttribute("lastname", lastname); model.addAttribute("firstname",
		 * firstname); model.addAttribute("email",email);
		 */
	  employeeInfo(model);
	  return"allSavingAccounts"; }
	  
	  
	  @RequestMapping(value = "/allCheckingAccountsForm") public String
	  allCheckingAccountsForm(Model model) {
	  List<CheckingAccount>chekings=dao.allCheckings();
	  model.addAttribute("checkings", chekings);
	  employeeInfo(model);
	  

		/*
		 * model.addAttribute("lastname",lastname); model.addAttribute("firstname",
		 * firstname); model.addAttribute("email", email);
		 */
	  
	  
	  return"allCheckingAccounts"; }
	  
	  
		@RequestMapping(value = "/empInfo")
		public void employeeInfo(Model model)
		            {
			Employee emp=null;
		        String URL_EMPLOYEE="http://employee-service/employeeInfo";
		       
		        try {
		        emp=oAuth2RestTemplate.getForObject(URL_EMPLOYEE, Employee.class);
		        		        
		        model.addAttribute("lastname", emp.getLastname());
				model.addAttribute("firstname", emp.getFirstname());
				model.addAttribute("email", emp.getEmail());
				photo=emp.getPhoto();
				code=emp.getCode();
			    System.out.println("LASTNAME: "+emp.getEmail());
			    
		        }catch (Exception e) {
					// TODO: handle exception
				}
		     }
	  
	  
	  
	  
	  @RequestMapping(value = "/getImage", produces = MediaType.IMAGE_JPEG_VALUE)
	  
	  @ResponseBody public byte[] getImage() throws IOException {
	  
	  ByteArrayInputStream b = null; byte[] io = null;
	  
	  MultiValueMap<Object, String>map=new LinkedMultiValueMap<Object, String>();
	  map.add("codeSearch", code); 
	  try { byte[] photos = photo; b = new
	  ByteArrayInputStream(photos);
	  
	  io = IOUtils.toByteArray(b); } catch (Exception e) {
	  
	  
	  }
	  
	  return io; }
	 
}
class Employee {
	private String lastname;
	private String firstname;
	private String email;
	private String code;
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	private byte[] photo;

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

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
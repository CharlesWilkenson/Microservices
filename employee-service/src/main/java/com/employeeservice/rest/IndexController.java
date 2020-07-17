package com.employeeservice.rest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;


import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.employeeservice.entities.Adress;
import com.employeeservice.entities.Employee;
import com.employeeservice.entities.Salary;
import com.employeeservice.services.Services;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Controller
public class IndexController {

 //private final Logger logger = LoggerFactory.getLogger(IndexController.class);
	@Autowired
	private OAuth2RestTemplate oAuth2RestTemplate ;
	@Autowired
	private Services dao;
	private String code;
	private	String lastname="";
	private	String firstname="";
	private	String email;
	byte[]photo=null;
	
	
		
	//	@HystrixCommand(fallbackMethod = "fallback")
		 @RequestMapping(value = "/saveEmployeeform")
		    public String saveEmployeForm(Principal principal,Model model) {
			 if(firstname.equals("") && lastname.equals("") && photo==null)
		         return 
		        		 employeeInfos(principal, model);
		     model.addAttribute("lastname", lastname);
					model.addAttribute("firstname", firstname);
					model.addAttribute("email", email);
		        return "saveEmploye";
		    }
		
		

		   // @SuppressWarnings("unused")
			// a fallback method to be called if failure happened
			public String fallback(Principal principal,Model model) {
				return "index";
			}
		
	//	@HystrixCommand(fallbackMethod = "fallback")
@GetMapping(value = "/getEmployeeImage", produces = MediaType.IMAGE_JPEG_VALUE)
@ResponseBody
public byte[] getImage() throws IOException {

	ByteArrayInputStream b = null;
	byte[] io = null;
	try {
	
	//	Employee emp=dao.findEmploye(code);
		byte[] picture = photo;
		b = new ByteArrayInputStream(picture);

		io = IOUtils.toByteArray(b);
	} catch (Exception e) {

	
	}

	return io;
}

	

	//	@HystrixCommand(fallbackMethod = "fallback")
 @RequestMapping(value = "/searchEmployeeform")
 public String searchEmployeForm(Principal principal, Model model) {
	 if(firstname.equals("") && lastname.equals("") && photo==null)
         return 
        		 employeeInfos(principal, model);
     model.addAttribute("lastname", lastname);
			model.addAttribute("firstname", firstname);
			model.addAttribute("email", email);
     return "searchEmploye";
 }

//	@HystrixCommand(fallbackMethod = "fallback")
@RequestMapping(value = "/listEmployeeform")
public String allEmployees(Principal principal,Model model) {

		 employeeInfos(principal, model);

List<Employee>lst=dao.findAllEmployes();


 model.addAttribute("lastname", lastname);
	model.addAttribute("firstname", firstname);
	model.addAttribute("email", email);
	model.addAttribute("employees", lst);
return "listerEmploye";
}

		
	//	@HystrixCommand(fallbackMethod = "fallback")

 public String employeeInfos(Principal principal,Model model) {
		String userInfo=principal.getName();
		//String role=principal.
		Optional<Employee>empl=dao.findByEmail(userInfo);
		System.out.println("INFOS "+empl.get().getEmail());
	if(empl.isPresent()){ 
		Employee em=empl.get();
			 code=em.getCode();
			 lastname=em.getLastname();
			 firstname=em.getFirstname();
			 email=em.getEmail();
			 photo=em.getPhoto();
			
	}
	            model.addAttribute("lastname", lastname);
				model.addAttribute("firstname", firstname);
				model.addAttribute("email", email);
     return "listerEmploye";
 }
 
 
		
	//	@HystrixCommand(fallbackMethod = "fallback")
 @RequestMapping(value = "/searchSalaryform")
 public String searchSalaryForm(Principal principal,Model model) {
	 if(firstname.equals("") && lastname.equals("") && photo==null)
         return 
        		 employeeInfos(principal, model);
     model.addAttribute("lastname", lastname);
			model.addAttribute("firstname", firstname);
			model.addAttribute("email", email);
     return "searchSalary";
 }
 
 
	//	@HystrixCommand(fallbackMethod = "fallback")
@RequestMapping(value = "/addSalaryform")
public String addSalaryForm(Principal principal,Model model) {
	 if(firstname.equals("") && lastname.equals("") && photo==null)
      return 
     		 employeeInfos(principal, model);
  model.addAttribute("lastname", lastname);
			model.addAttribute("firstname", firstname);
			model.addAttribute("email", email);
  return "addSalary";
}

 



 
		
	//	@HystrixCommand(fallbackMethod = "fallback")
 @RequestMapping(value = "/listSalaryform")
 public String listSalaryForm(Principal principal,Model model) {
	 if(firstname.equals("") && lastname.equals("") && photo==null)
         return 
        		 employeeInfos(principal, model);
     model.addAttribute("lastname", lastname);
			model.addAttribute("firstname", firstname);
			model.addAttribute("email", email);
     return "listerSalaries";
 }
 
 
	/*
	 * //@HystrixCommand(fallbackMethod = "fallback")
	 * // @PreAuthorize("hasAnyRole('ADMIN')")
	 * 
	 * @RequestMapping(value = "saveClientForm") public String saveClientForm(Model
	 * model,Principal principal) { if(firstname.equals("") && lastname.equals("")
	 * && photo==null) return employeeInfos(principal, model);
	 * model.addAttribute("lastname", lastname); model.addAttribute("firstname",
	 * firstname); model.addAttribute("email", email); return "saveClient"; }
	 * 
	 * // @PreAuthorize("hasAnyRole('ADMIN')")
	 * 
	 * @RequestMapping(value = "/searchClientForm") public String
	 * searchClientForm(Model model,Principal principal) { if(firstname.equals("")
	 * && lastname.equals("") && photo==null) return employeeHomePage(principal,
	 * model); model.addAttribute("lastname", lastname);
	 * model.addAttribute("firstname", firstname); model.addAttribute("email",
	 * email); return "searchClient"; }
	 * 
	 * 
	 * 
	 * //@HystrixCommand(fallbackMethod = "fallback")
	 * // @PreAuthorize("hasAnyRole('ADMIN')")
	 * 
	 * @RequestMapping(value = "/allClientsForm") public String allClientsForm(Model
	 * model,Principal principal) { if(firstname.equals("") && lastname.equals("")
	 * && photo==null) return employeeHomePage(principal, model); String
	 * URL_CLIENT="http://client-service/allclients";
	 * Client[]clients=oAuth2RestTemplate.getForObject(URL_CLIENT, Client[].class);
	 * model.addAttribute("clients", clients); model.addAttribute("lastname",
	 * lastname); model.addAttribute("firstname", firstname);
	 * model.addAttribute("email", email); return "allClients"; }
	 * 
	 * 
	 * 
	 * 
	 * //@HystrixCommand(fallbackMethod = "fallback")
	 * 
	 * @RequestMapping(value = "/saveForm") public String index(Model
	 * model,Principal principal) { if(firstname.equals("") && lastname.equals("")
	 * && photo==null) return employeeHomePage(principal, model);
	 * model.addAttribute("lastname", lastname); model.addAttribute("firstname",
	 * firstname); model.addAttribute("email", email); return"saveOperation"; }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * //@HystrixCommand(fallbackMethod = "fallback")
	 * 
	 * @RequestMapping(value = "/addAccountForm") public String addAccountForm(Model
	 * model,Principal principal) { if(firstname.equals("") && lastname.equals("")
	 * && photo==null) return employeeHomePage(principal, model);
	 * model.addAttribute("lastname", lastname); model.addAttribute("firstname",
	 * firstname); model.addAttribute("email", email); return"addAccount"; }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * //@HystrixCommand(fallbackMethod = "fallback")
	 * 
	 * @RequestMapping(value = "/searchAccountForm") public String
	 * searchAccountForm(Model model,Principal principal) { if(firstname.equals("")
	 * && lastname.equals("") && photo==null) return employeeHomePage(principal,
	 * model); model.addAttribute("lastname", lastname);
	 * model.addAttribute("firstname", firstname); model.addAttribute("email",
	 * email); return"searchAccount"; }
	 * 
	 * 
	 * 
	 * 
	 * // @HystrixCommand(fallbackMethod = "fallback")
	 * 
	 * @RequestMapping("/alldepositsForm") public String alldeposits(Model
	 * model,Principal principal) { if(firstname.equals("") && lastname.equals("")
	 * && photo==null) return employeeHomePage(principal, model); String
	 * url="http://operation-service/alldeposits"; Deposit[] response =
	 * oAuth2RestTemplate.getForObject(url, Deposit[].class);
	 * model.addAttribute("deposits", response); model.addAttribute("lastname",
	 * lastname); model.addAttribute("firstname", firstname); return"alldeposits"; }
	 * 
	 * 
	 * 
	 * 
	 * //@HystrixCommand(fallbackMethod = "fallback")
	 * 
	 * @RequestMapping("/allwithdrawalsForm") public String allwithdrawals(Model
	 * model,Principal principal) { if(firstname.equals("") && lastname.equals("")
	 * && photo==null) return employeeHomePage(principal, model);
	 * 
	 * String url="http://operation-service/allwithdrawals"; Withdrawal[] response =
	 * oAuth2RestTemplate.getForObject(url, Withdrawal[].class);
	 * 
	 * model.addAttribute("withdrawals", response); model.addAttribute("lastname",
	 * lastname); model.addAttribute("firstname", firstname);
	 * return"allwithdrawals"; }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * //@HystrixCommand(fallbackMethod = "fallback")
	 * 
	 * @RequestMapping("/alltransfertsForm") public String alltransferts(Model
	 * model,Principal principal) { if(firstname.equals("") && lastname.equals("")
	 * && photo==null) return employeeHomePage(principal, model); String
	 * url="http://operation-service/alltransferts";
	 * 
	 * Transfert[] response = oAuth2RestTemplate.getForObject(url,
	 * Transfert[].class);
	 * 
	 * model.addAttribute("transferts", response); model.addAttribute("email",
	 * email); model.addAttribute("lastname", lastname);
	 * model.addAttribute("firstname", firstname); return"alltransferts"; }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * // @HystrixCommand(fallbackMethod = "fallback")
	 * 
	 * @RequestMapping(value = "/allAccountsForm") public String
	 * allAccountsForm(Model model,Principal principal) { if(firstname.equals("") &&
	 * lastname.equals("") && photo==null) return employeeHomePage(principal,
	 * model); String URL_ACCOUNT="http://operation-service/allAccountsForm";
	 * Account[] accounts=oAuth2RestTemplate.getForObject(URL_ACCOUNT,
	 * Account[].class); model.addAttribute("accounts", accounts);
	 * model.addAttribute("lastname", lastname); model.addAttribute("firstname",
	 * firstname); model.addAttribute("email", email); return"allAccounts"; }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * //@HystrixCommand(fallbackMethod = "fallback")
	 * 
	 * @RequestMapping(value = "/allSavingAccountsForm") public String
	 * allSavingAccountsForm(Model model,Principal principal) {
	 * if(firstname.equals("") && lastname.equals("") && photo==null) return
	 * employeeHomePage(principal, model);
	 * 
	 * String URL_ACCOUNT="http://operation-service/allSavings"; SavingsAccount[]
	 * savings=oAuth2RestTemplate.getForObject(URL_ACCOUNT, SavingsAccount[].class);
	 * model.addAttribute("savings", savings); model.addAttribute("lastname",
	 * lastname); model.addAttribute("firstname", firstname);
	 * model.addAttribute("email", email); return"allSavingAccounts"; }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * // @HystrixCommand(fallbackMethod = "fallback")
	 * 
	 * @RequestMapping(value = "/allCheckingAccountsForm") public String
	 * allCheckingAccountsForm(Model model,Principal principal) {
	 * if(firstname.equals("") && lastname.equals("") && photo==null) return
	 * employeeHomePage(principal, model); String
	 * URL_ACCOUNT="http://operation-service/allChekings"; CheckingAccount[]
	 * chekings=oAuth2RestTemplate.getForObject(URL_ACCOUNT,
	 * CheckingAccount[].class);
	 * 
	 * model.addAttribute("checkings", chekings); model.addAttribute("lastname",
	 * lastname); model.addAttribute("firstname", firstname);
	 * model.addAttribute("email", email); return"allCheckingAccounts"; }
	 * 
	 */

		
		
		
		
		
		
		
		
		
		
		
}


class CheckingAccount{
	
	String accountNumber;
	double balance;
	public CheckingAccount() {
		super();
		// TODO Auto-generated constructor stub
	}

	String currency;
	boolean active;
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

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	Date date;String client;
	
}

class SavingsAccount{
	
	String accountNumber;
	double balance;
	String currency;
	public SavingsAccount() {
		super();
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
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	boolean active;
	Date date;String client;
	double rate;
}
class Client{
	
	private String code;
	 private String lastname;
	 private String firstname;
	 private String sex;                         
	 private String dateBirth;
	 private String citizenship;
	 private Adress adress;
	 public Client(String code, String lastname, String firstname, String sex, String dateBirth, String citizenship,
			String email, String identification, String phone, Adress adress) {
		super();
		this.code = code;
		this.lastname = lastname;
		this.firstname = firstname;
		this.sex = sex;
		this.dateBirth = dateBirth;
		this.citizenship = citizenship;
		this.adress = adress;
		this.identification = identification;
		this.phone = phone;
		this.email = email;
	}
	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Adress getAdress() {
		return adress;
	}
	public void setAdress(Adress adress) {
		this.adress = adress;
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
	private String identification;
	 private String phone;
	 private String email;
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



class Deposit{
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getOperationDate() {
		return operationDate;
	}
	public void setOperationDate(Date operationDate) {
		this.operationDate = operationDate;
	}
	private long id;
	private double amount;
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	private Account account;
	private Date operationDate;
}

class Transfert{
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getOperationDate() {
		return operationDate;
	}
	public void setOperationDate(Date operationDate) {
		this.operationDate = operationDate;
	}
	public String getBeneficiaryNumber() {
		return beneficiaryNumber;
	}
	public void setBeneficiaryNumber(String beneficiaryNumber) {
		this.beneficiaryNumber = beneficiaryNumber;
	}
	private long id;
	private double amount;
	private Account account;
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	private Date operationDate;
	private String beneficiaryNumber;
}
class Withdrawal{
	
	private long id;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getOperationDate() {
		return operationDate;
	}
	public void setOperationDate(Date operationDate) {
		this.operationDate = operationDate;
	}
	private double amount;
	private Account account;
	private Date operationDate;
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}

}
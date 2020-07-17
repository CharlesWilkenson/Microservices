package org.clientspace.restController;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

   @Controller
       public class indexController {
    	   @Autowired
    	   private OAuth2RestTemplate restTemplate;
    	   
    	    private String accountNumber="";  
    	    String beneficiaryNumber;
    		private	String lastname="";
    		private	String firstname="";
    	   
	 @RequestMapping("/alldeposits")
		public String alldeposits(Model model) {
		 
		 if(accountNumber.equals("")&& lastname.equals("") && firstname.equals(""))
			 return clientHomePage();
		 
		 
			String url="http://operation-service/alldeposits/"+accountNumber;	
			  Deposit[] response  = restTemplate.getForObject(url, Deposit[].class); 
			  double sum=0;
			  for(Deposit d:response) {
				  sum +=d.getAmount();
				  System.out.println("Amount of money :"+d.getAmount());
			  }
					
		model.addAttribute("deposits", response);
		model.addAttribute("sumdeposits", sum);
		 model.addAttribute("lastname", lastname);
		 model.addAttribute("firstname", firstname);
		return"alldeposits";
	}
	
	
	 @RequestMapping("/allwithdrawals")
		public String allwithdrawals(Model model) {
		 
		 if(accountNumber.equals("")&& lastname.equals("") && firstname.equals(""))
			 return clientHomePage();
		 
			String url="http://operation-service/allwithdrawals/"+accountNumber;
			  double sum=0;
			  
			Withdrawal[] response  = restTemplate.getForObject(url, Withdrawal[].class); 
					  
					  for(Withdrawal d:response) {
						  sum +=d.getAmount();
						  System.out.println("Amount of money :"+d.getAmount());
					  }
	        model.addAttribute("withdrawals", response);
	        model.addAttribute("sumwithdrawals", sum);
	        model.addAttribute("lastname", lastname);
			model.addAttribute("firstname", firstname);
			return"allwithdrawals";
		}
	 
	 @RequestMapping("/alltransferts")
		public String alltransferts(Model model) {
		 if(accountNumber.equals("")&& lastname.equals("") && firstname.equals(""))
			 return clientHomePage();
			String url="http://operation-service/alltransferts/"+accountNumber;
			  double sum=0;
			  
			Transfert[] response  = restTemplate.getForObject(url, Transfert[].class); 
					  
					  for(Transfert d:response) {
						  sum +=d.getAmount();
					 System.out.println("Amount of money :"+d.getAmount());
					  }
					    model.addAttribute("transferts", response);
				        model.addAttribute("sumtransferts", sum);
				        model.addAttribute("lastname", lastname);
						 model.addAttribute("firstname", firstname);
			            return"alltransferts";
		}
	

	 
	 
	 @RequestMapping("/myprofil")
		public String myprofil(Model model) {
		 if(accountNumber.equals("")&& lastname.equals("") && firstname.equals(""))
			 return clientHomePage();
		 
		   
				
				MultiValueMap<Object, String>mapclient=new LinkedMultiValueMap<Object, String>();
				mapclient.add("codeSearch", accountNumber);
				
				String URL_CLIENT="http://client-service/findClient";
			
				ResponseEntity<Client> response2 = null;
				try {
				
					response2 = restTemplate.postForEntity(URL_CLIENT, mapclient, Client.class);

					
						lastname=response2.getBody().getLastname();
						firstname= response2.getBody().getFirstname();
						
						  model.addAttribute("lastname", response2.getBody().getLastname());
						  model.addAttribute("firstname", response2.getBody().getFirstname());
						  model.addAttribute("sex", response2.getBody().getSex());
						  model.addAttribute("dateBirth", response2.getBody().getDateBirth());
						  model.addAttribute("citizenship", response2.getBody().getCitizenship());
						  model.addAttribute("email", response2.getBody().getEmail());
						  model.addAttribute("city", response2.getBody().getAdress().getCity());
						  model.addAttribute("street", response2.getBody().getAdress().getStreet());
						  model.addAttribute("number", response2.getBody().getAdress().getNumber());
						 
					
						   
				} catch (RestClientException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 
		 
		 
		 model.addAttribute("lastname", lastname);
		 model.addAttribute("firstname", firstname);
			return"myprofil";
		}
	 
	 
	 @RequestMapping("/clientDashboard")
		public String clientDashboard(Model model) {
		 if(accountNumber.equals("")&& lastname.equals("") && firstname.equals(""))
			 return clientHomePage();
		 
		 alltransferts(model);
		 allwithdrawals( model) ;
		 alldeposits( model) ;
		 model.addAttribute("lastname", lastname);
		 model.addAttribute("firstname", firstname);
			return"index";
		}
	
	 
	 
	 @RequestMapping("/clientHomePage")
		public String clientHomePage() {
			 lastname="";
			 firstname="";
			 accountNumber="";
			return"clientHomePage";
		}
	 
	 @RequestMapping("/makeTransfert")
		public String makeTransfert(Model model) {
		 if(accountNumber.equals("")&& lastname.equals("") && firstname.equals(""))
			 return clientHomePage();
		 
		 model.addAttribute("lastname", lastname);
		 model.addAttribute("firstname", firstname);
			return"makeTransfert";
		}
	 
	 @RequestMapping("/myaccount")
	 public String myaccount(Model model) {
		 if(accountNumber.equals("")&& lastname.equals("") && firstname.equals(""))
			 return clientHomePage();
		 
			MultiValueMap<Object, String>map=new LinkedMultiValueMap<Object, String>();
			map.add("account_number", accountNumber);
			String URL_ACCOUNT="http://operation-service/findAccount";
			
			ResponseEntity<Account> response = null;
			try {
				response = restTemplate.postForEntity(URL_ACCOUNT, map, Account.class);
				    accountNumber=response.getBody().accountNumber;
					
					  model.addAttribute("accountNumber", response.getBody().accountNumber);
					  model.addAttribute("balance", response.getBody().balance);
					  model.addAttribute("currency", response.getBody().currency);
					  model.addAttribute("date", response.getBody().date);
					  model.addAttribute("active", response.getBody().active);
			
					  model.addAttribute("lastname", lastname);
					  model.addAttribute("firstname", firstname);
							   
			} catch (RestClientException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "myaccount";
		}
	 
	 
	 @PostMapping(value="/getAccount")
		public ResponseEntity<Object> getAccountNumber(Model model,@RequestParam("account_number")String numCompte ) {
		    model.addAttribute("account_number", numCompte);	 
			MultiValueMap<Object, String>map=new LinkedMultiValueMap<Object, String>();
			map.add("account_number", numCompte);
			
			MultiValueMap<Object, String>mapclient=new LinkedMultiValueMap<Object, String>();
			mapclient.add("codeSearch", numCompte);
			
			String URL_ACCOUNT="http://operation-service/findAccount";
			String URL_CLIENT="http://client-service/findClient";
			
			ResponseEntity<Account> response = null;
			ResponseEntity<Client> response2 = null;
			try {
				response = restTemplate.postForEntity(URL_ACCOUNT, map, Account.class);
				response2 = restTemplate.postForEntity(URL_CLIENT, mapclient, Client.class);
				   
				    accountNumber=response.getBody().accountNumber;
					lastname=response2.getBody().getLastname();
					firstname= response2.getBody().getFirstname();					   
						
				System.out.println(" "+numCompte+" "+response.getBody().balance+" "+response.getBody().currency);
			} catch (RestClientException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return new ResponseEntity<Object>(response,HttpStatus.OK);
		}
	
}

   class Client{
	   
		private String lastname;
		 private String firstname;
		 private String sex;                         
		 private String dateBirth;
		 private String citizenship;
		 public String getLastname() {
			return lastname;
		}
		public void setLastname(String lastname) {
			this.lastname = lastname;
		}
		public Client(String lastname, String firstname, String sex, String dateBirth, String citizenship,
				String identification, String phone, String email, Adress adress) {
			super();
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
		public Client() {
			super();
			// TODO Auto-generated constructor stub
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
		private String identification;
		 private String phone;
		 private String email;
		 private Adress adress;
   }
   
   class Adress{
	   private String adress_id;
	   private String city;
	   private String street;
	   private int number;
	public Adress() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Adress(String city, String street, int number) {
		super();
		this.city = city;
		this.street = street;
		this.number = number;
	}
	public String getAdress_id() {
		return adress_id;
	}
	public void setAdress_id(String adress_id) {
		this.adress_id = adress_id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
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
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public Date getOperationDate() {
		return operationDate;
	}
	public void setOperationDate(Date operationDate) {
		this.operationDate = operationDate;
	}
	private long id;
	private double amount;
	private String accountNumber;
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
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
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
	private String accountNumber;
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
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public Date getOperationDate() {
		return operationDate;
	}
	public void setOperationDate(Date operationDate) {
		this.operationDate = operationDate;
	}
	private double amount;
	private String accountNumber;
	private Date operationDate;

}
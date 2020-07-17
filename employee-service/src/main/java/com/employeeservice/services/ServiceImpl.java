package com.employeeservice.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeeservice.entities.Adress;
import com.employeeservice.entities.Employee;
import com.employeeservice.entities.Salary;
import com.employeeservice.entities.SendEmail;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service

public class ServiceImpl implements Services{

	@Autowired
	private EmployeRepository  employeRepository;
	@Autowired
	private AdressRepository adressRepository ;
	@Autowired
	private SalaryRepository salaryRepository;
	
	@Transactional
	@Override

	public void saveEmploye(Employee e,Adress a,Salary salary) {
		    e.setAdress(a);  		    
		    e.setDate(new Date());
		    e.setSalary(salary);
		adressRepository.save(a);	
	    salaryRepository.save(salary);
		employeRepository.save(e);

		//SendEmail.sendEmail(encrytePassword(password()), e.getEmail());
	}

	
	@Override
	public Employee findEmploye(String code) {
				
		  Optional<Employee> em=employeRepository.findById(code); 
		  
		  if(!em.isPresent()) {
		  throw new RuntimeException("Code invalide");
		  
		  }		 
		 //Employee e=employeRepository.getOne(code);
		 return em.get();
	}

	@Override
	public List<Employee> findAllEmployes() {
		// TODO Auto-generated method stub
		return employeRepository.findAll();
	}

	@Transactional
	@Override
	public void updateEmployes(Employee e,Adress a) {
		adressRepository.save(a);
		e.setAdress(a);
		employeRepository.save(e);
		
		
		
	}

	@Override
	public void addAdress(Adress a) {
		// TODO Auto-generated method stub
		
	}
	public static String encrytePassword(String password) {
		//  BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		//encoder.encode(password);
		return""; 
		    }

	public  String password(){
		Random rd=new Random();

	String id=String.format("%s-%s-%s-%s", rd.nextInt(9999), 
			rd.nextInt(9999), rd.nextInt(9999), rd.nextInt(9999));
	return	id;
	}

	@Override
	public List<Employee> findByCode(String code) {
		// TODO Auto-generated method stub
		return employeRepository.findByCode(code);
	}





	@Override
	public void updateSalary(Salary salary) {
		System.out.println("SAL1 "+salary.getSalary_id());
		Optional<Salary> s=salaryRepository.findById(salary.getSalary_id());
		System.out.println("SAL2 "+s.get().getSalary_id());
		if(s.isPresent()) {
			
			salaryRepository.save(salary);	
			System.out.println("SAL3 "+salary.getSalary_id());
		}
		
			
		
	}


	@Override
	public List<Salary> allSalaries() {
	
		return salaryRepository.findAll();
	}


	@Override
	public Optional<Employee> findByEmail(String email) {
		return employeRepository.findByEmail(email);
	}


	@Override
	public void addSalary(String code, Salary salary) {
		
  Optional<Employee> em=employeRepository.findById(code); 
  
  System.out.println("Method is called "+code);
  try {
  if(em.isPresent()) {
salaryRepository.save(salary);
Employee employee=em.get();
employee.setSalary(new Salary(salary.getSalary_id()));
employeRepository.save(employee);
  
  System.out.println(salary.getSalary_id()+ employee.getEmail()+employee.getCode());
  }
  
  }	catch (Exception e) {
	  System.out.println("Error "+e.getMessage());
}
		
	
	}


	@Override
	public Salary getSalary(String code) {
	
		Optional<Salary> optional=salaryRepository.findById(code);
		if(optional.isPresent()) {
			
			return optional.get();
		}
		
		return null;
	}
}

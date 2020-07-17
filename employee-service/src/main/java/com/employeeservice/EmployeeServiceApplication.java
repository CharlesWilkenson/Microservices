package com.employeeservice;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

import com.employeeservice.entities.Employee;
import com.employeeservice.services.EmployeRepository;

@SpringBootApplication
@EnableCircuitBreaker 
public class EmployeeServiceApplication implements CommandLineRunner{

	@Autowired private EmployeRepository employeRepository;
	public static void main(String[] args) {
		SpringApplication.run(EmployeeServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal;
		/*
		 * List<Employee>empl=employeRepository.findByEmail(
		 * "charleswilkenson13@gmail.com"); for(Employee em:empl) {
		 * System.out.println("LASTNAME: "+em.getLastname()+" FIRSTNAME: "+em.
		 * getFirstname()); }
		 */
	}

}

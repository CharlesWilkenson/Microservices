package org.ClientService.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.ClientService.entities.Client;
import org.ClientService.entities.Person;
import org.ClientService.services.Services;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class IndexController {
	@Autowired
	private Services dao;
private String code;
	@Autowired
	private OAuth2RestTemplate oAuth2RestTemplate;
private byte[]photo;

	// @PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "saveClientForm")
	public String saveClientForm(Model model) {
		employeeInfo(model);
		return "saveClient";
	}

	// @PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/searchClientForm")
	public String searchClientForm(Model model) {
		employeeInfo(model);
		return "searchClient";
	}
	
	// @PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/allClientsForm")
	public String allClientsForm(Model model) {
		employeeInfo(model);		
		List<Client> clients = dao.findAllClients();
		model.addAttribute("clients", clients);
		return "allClients";
	}

	

	@RequestMapping(value = "/empInfo")
	public ResponseEntity<Object> employeeInfo(Model model)
	            {
	        String URL_EMPLOYEE="http://employee-service/employeeInfo";
	        Employee emp=oAuth2RestTemplate.getForObject(URL_EMPLOYEE, Employee.class);
	        model.addAttribute("lastname", emp.getLastname());
			model.addAttribute("firstname", emp.getFirstname());
			model.addAttribute("email", emp.getEmail());
			photo=emp.getPhoto();
			code=emp.getCode();
		    System.out.println("LASTNAME: "+emp.getEmail());		
		    return new ResponseEntity<Object>(emp,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getImage", produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getImage() throws IOException {

		ByteArrayInputStream b = null;
		byte[] io = null;
		
		MultiValueMap<Object, String>map=new LinkedMultiValueMap<Object, String>();
		map.add("codeSearch", code);
		try {
	        byte[] photos = photo;
			b = new ByteArrayInputStream(photos);

			io = IOUtils.toByteArray(b);
		} catch (Exception e) {

		
		}

		return io;
	}
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


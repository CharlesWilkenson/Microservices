package org.ClientService.controller;


import org.ClientService.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestOperations;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class PersonInfoController {

	@Autowired
	private OAuth2RestOperations restOperations;
    @GetMapping("/person1")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public @ResponseBody Person personInfo() {
        return new Person("abir", "Dhaka", "Bangladesh", 29, "Male");
    }  
    
    
    @GetMapping("/personInfo1")
    public ModelAndView person() {
        ModelAndView mav = new ModelAndView("personInfo");
        String personResourceUrl = "http://localhost:4949/person2";
        mav.addObject("person", restOperations.getForObject(personResourceUrl, String.class));
        return mav;
    }

}
package com.employeeservice.config;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.client.RestTemplate;

/**
 *  REST API Resource Server.
 */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true) // Allow method annotations like @PreAuthorize
public class ResourceConfigurer extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.NEVER)
            .and()
            .authorizeRequests()
            .anyRequest().authenticated();
    }
	 @LoadBalanced	
	  @Bean public OAuth2RestTemplate loadBalancedOauth2RestTemplate(
	  OAuth2ProtectedResourceDetails resource, OAuth2ClientContext context) {
	  return new OAuth2RestTemplate(resource, context); }
	 
    
	/*
	 * @Bean public OAuth2ProtectedResourceDetails resource() {
	 * ClientCredentialsResourceDetails resourceDetails = new
	 * ClientCredentialsResourceDetails(); resourceDetails.setAccessTokenUri(
	 * "http://localhost:7070/authorization-server/oauth/token");
	 * resourceDetails.setClientId("authserver");
	 * resourceDetails.setClientSecret("passwordforauthserver");
	 * resourceDetails.setScope(Arrays.asList("myscope")); return resourceDetails; }
	 * 
	 * @Bean
	 * 
	 * @LoadBalanced protected RestTemplate restTemplate() { return new
	 * OAuth2RestTemplate(resource()); }
	 */

	    
	    

}
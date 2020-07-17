package org.clientspace.confi;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

	@Configuration
	//@EnableOAuth2Sso
	@EnableResourceServer
	@EnableGlobalMethodSecurity(prePostEnabled = true) // Allow method annotations like @PreAuthoriz
	public class ResourceServer
	    extends ResourceServerConfigurerAdapter {

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
		 
	}
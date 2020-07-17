package org.ClientService.config;


import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
//@EnableOAuth2Sso
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true) // Allow method annotations like @PreAuthoriz
public class SiteSecurityConfigurer
    extends ResourceServerConfigurerAdapter {

	  @Override
	    public void configure(HttpSecurity http) throws Exception {
	        http.sessionManagement()
	            .sessionCreationPolicy(SessionCreationPolicy.NEVER)
	            .and()
	            .authorizeRequests()
	            .anyRequest().authenticated();

	    }
	  
	  
	   @LoadBalanced // Load balance between service instances running at different ports.
	
	   @Bean // Create a bean for restTemplate to call services
	   public OAuth2RestTemplate loadBalancedOauth2RestTemplate(
	  OAuth2ProtectedResourceDetails resource, OAuth2ClientContext context) {
	  return new OAuth2RestTemplate(resource, context); }
	 
}
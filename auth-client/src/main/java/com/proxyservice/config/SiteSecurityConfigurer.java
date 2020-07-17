package com.proxyservice.config;


import java.util.Arrays;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

@EnableZuulProxy
@Configuration
@EnableOAuth2Sso
@EnableWebSecurity
public class SiteSecurityConfigurer
    extends
        WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http)
        throws Exception {
    	http.csrf().disable();
        http.antMatcher("/**")
            .authorizeRequests()
            .antMatchers("/", "/webjars/**")
            .permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .logout()
            .logoutSuccessUrl("/")
            .permitAll()
		/*
		 * .and() .csrf() .csrfTokenRepository( CookieCsrfTokenRepository
		 * .withHttpOnlyFalse())
		 */;
    }

	  @Bean public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
	  StrictHttpFirewall firewall = new StrictHttpFirewall();
	  firewall.setAllowedHttpMethods(Arrays.asList("GET", "POST","PUT"));
	  firewall.setAllowSemicolon(true); firewall.setAllowUrlEncodedSlash(true);
	  firewall.setAllowBackSlash(true); firewall.setAllowUrlEncodedPercent(true);
	  firewall.setAllowUrlEncodedPeriod(true); return firewall; }
    
	@Override
	public void configure(WebSecurity web) throws Exception {
		web
		.ignoring()
		.antMatchers("/webjars/**","/**/*.css", "/**/*.png", "/**/*.gif",
        "/**/*.png","/**/*.jpg", "/**/*.js", "/**/*.map","/static/**",
        "/public/**", "/**/*.ttf", "/**/*.woff", "/**/*.woff2",
        "/**/*.svg", "/**/*.otf", "/**/*.eot");
		
		 web.httpFirewall(allowUrlEncodedSlashHttpFirewall());
	}
    
    @Bean
    public OAuth2RestOperations restOperations(
        OAuth2ProtectedResourceDetails resource,
        OAuth2ClientContext context) {
        return new OAuth2RestTemplate(resource, context);
    }
    }
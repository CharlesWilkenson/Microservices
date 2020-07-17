package com.example.authserver.config;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@Configuration
@EnableWebSecurity
@EnableOAuth2Client
public class WebSecurityConfigurer
    extends
        WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource datasource;
	@Autowired
    CustomSuccessHandler customSuccessHandler;
    @Override
    protected void configure(HttpSecurity http)
        throws Exception {
        http.authorizeRequests().antMatchers("/authorization-server/createUser").hasAnyRole("USER","ADMIN");
        http
            .authorizeRequests()       
            .antMatchers("/login**").permitAll()
            .anyRequest().authenticated()
            .and().csrf()
            .and().formLogin().loginPage("/login") 
            .successHandler(customSuccessHandler)
            .and()
            .logout()
		  .logoutSuccessUrl("/login")
		  .logoutUrl("/logout")
		  .permitAll();

    }

    @Override
    protected void configure(
        AuthenticationManagerBuilder auth) throws Exception {
		/*
		 * auth .inMemoryAuthentication()
		 * .withUser("user").password(passwordEncoder().encode("user")) .roles("USER")
		 * .and() .withUser("admin").password(passwordEncoder().encode("admin"))
		 * .roles("USER", "ADMIN");
		 */
        
		
		  auth.jdbcAuthentication().dataSource(datasource).usersByUsernameQuery(
		  "SELECT email as principal,password as credentials, active FROM user WHERE email=?"
		  ) .authoritiesByUsernameQuery(
		  "SELECT user_id as principal ,role_id as role FROM user_roles WHERE user_id=?"
		  ) .rolePrefix("ROLE_").passwordEncoder(passwordEncoder());
		 
        
        
    }

    @Override
    @Bean(name = "userDetailsService")
    public UserDetailsService userDetailsServiceBean()
        throws Exception {
        return super.userDetailsServiceBean();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Override
	public void configure(WebSecurity web) throws Exception {
		web
		.ignoring()
		.antMatchers("/webjars/**","/**/*.css", "/**/*.png", "/**/*.gif",
        "/**/*.png","/**/*.jpg", "/**/*.js", "/**/*.map","/static/**",
        "/public/**", "/**/*.ttf", "/**/*.woff", "/**/*.woff2",
        "/**/*.svg", "/**/*.otf", "/**/*.eot");
		
		// web.httpFirewall(allowUrlEncodedSlashHttpFirewall());
	}
}
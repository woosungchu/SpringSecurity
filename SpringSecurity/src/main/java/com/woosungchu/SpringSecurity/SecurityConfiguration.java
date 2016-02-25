package com.woosungchu.SpringSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
//Indicates that a class declares one or more @Bean methods and may be processed by the Spring container to generate bean definitions and service requests for those beans at runtime,
@EnableWebSecurity
//Add this annotation to an @Configuration class to have the Spring Security configuration defined
//in any WebSecurityConfigurer or more likely by extending the WebSecurityConfigurerAdapter base class and overriding individual methods:
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	 @Autowired
	    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
//AuthenticationManager which is responsible for processing any authentication request.
//We have used in-memory authentication while you are free to choose from JDBC, LDAP and other authentications.
	        auth.inMemoryAuthentication().withUser("bill").password("abc123").roles("USER");
	        auth.inMemoryAuthentication().withUser("admin").password("root123").roles("ADMIN");
	        auth.inMemoryAuthentication().withUser("dba").password("root123").roles("ADMIN","DBA");//dba have two roles.
	    }

}

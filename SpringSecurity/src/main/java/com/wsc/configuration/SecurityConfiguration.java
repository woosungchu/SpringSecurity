package com.wsc.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//URL Soure : http://websystique.com/spring-security/spring-security-4-hello-world-annotation-xml-example/

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

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//By default it will be applied to all requests, but can be restricted using requestMatcher(RequestMatcher)/antMathchers or other similar methods.
		http.authorizeRequests()
			.antMatchers("/","/home").permitAll()
			.antMatchers("/admin/**").access("hasRole('ADMIN')")
			.antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
			.and().formLogin().loginPage("/login")
			.usernameParameter("ssoId").passwordParameter("password")
			.and().csrf()
			.and().exceptionHandling().accessDeniedPage("/Access_Denied");

	}
}

/*
 *<?xml version="1.0" encoding="UTF-8"?>
<beans:beans xmlns="http://www.springframework.org/schema/security"
    xmlns:beans="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-4.1.xsd
    http://www.springframework.org/schema/security http://www.springframework.org/schema/security/spring-security-4.0.xsd">

    <http auto-config="true" >
        <intercept-url pattern="/" access="permitAll" />
        <intercept-url pattern="/home" access="permitAll" />
        <intercept-url pattern="/admin**" access="hasRole('ADMIN')" />
        <intercept-url pattern="/dba**" access="hasRole('ADMIN') and hasRole('DBA')" />
        <form-login  login-page="/login" username-parameter="ssoId" password-parameter="password" authentication-failure-url="/Access_Denied" />
        <csrf/>
    </http>

    <authentication-manager >
        <authentication-provider>
            <user-service>
                <user name="bill"  password="abc123"  authorities="ROLE_USER" />
                <user name="admin" password="root123" authorities="ROLE_ADMIN" />
                <user name="dba"   password="root123" authorities="ROLE_ADMIN,ROLE_DBA" />
            </user-service>
        </authentication-provider>
    </authentication-manager>


</beans:beans>
 *
*/
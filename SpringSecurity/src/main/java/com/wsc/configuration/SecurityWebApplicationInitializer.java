package com.wsc.configuration;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
//After you have done Security Configuration,
//this specified initialize class register the springSecurityFilter with application war.
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

//Or you can configure springSecurityFilterChain by put code below into .xml

	/*
    <filter>
	    <filter-name>springSecurityFilterChain</filter-name>
	    <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
	</filter>

	<filter-mapping>
	    <filter-name>springSecurityFilterChain</filter-name>
	    <url-pattern>/*</url-pattern>
	</filter-mapping>

	 */
}

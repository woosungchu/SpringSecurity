package com.wsc.configuration;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
//After you have done Security Configuration,
//this specified initialize class register the springSecurityFilter with application war.
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {
//After then, you should configure springSecurityFilterChain also into .xml
}

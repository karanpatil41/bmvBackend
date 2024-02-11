package com.bmv.security;

import java.io.IOException;
import java.io.PrintWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//Steps to implement jwt token:
//1)  Make sure spring-boot-starter-security is there in pom.xml

//2)  Create Class JWTAthenticationEntryPoint that implement AuthenticationEntryPoint. 
//Method of this class is called whenever as exception is thrown due to unauthenticated 
//user trying to access the resource that required authentication.
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
	
	private Logger logger = LoggerFactory.getLogger(OncePerRequestFilter.class);
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		PrintWriter writer = response.getWriter();
		logger.info("JwtAuthenticationEntryPoint class--commence()",writer);
		writer.println("Access Denied !! " + authException.getMessage());
	}

}

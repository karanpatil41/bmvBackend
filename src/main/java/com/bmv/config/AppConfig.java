package com.bmv.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class AppConfig {
	
	//Whenever loadUserByName gets called then user will fetch from here
//	@Bean
//	public UserDetailsService userDetailsService() { 
//		UserDetails user =User.builder().username("harsh").password(passwordEncode().encode("abc")).roles("ADMIN").build();
//		UserDetails user1 =User.builder().username("karan").password(passwordEncode().encode("abc")).roles("ADMIN").build();
//		return new InMemoryUserDetailsManager(user,user1);
//	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

}

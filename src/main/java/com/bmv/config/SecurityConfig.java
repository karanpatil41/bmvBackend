package com.bmv.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bmv.security.JwtAuthenticationEntryPoint;
import com.bmv.security.JwtAuthenticationFilter;
import com.bmv.services.impl.CustomUserDetailService;

//Configure spring security in configuration file:
@Configuration
public class SecurityConfig {

	@Autowired
	private JwtAuthenticationEntryPoint point;

	@Autowired
	private JwtAuthenticationFilter filter;

	@Autowired
	private UserDetailsService userDetailsService;

	private Logger logger = LoggerFactory.getLogger(OncePerRequestFilter.class);

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		logger.info("SecurityConfig class--securityFilterChain()", http);

		// configuration
		http.csrf(csrf -> csrf.disable()).cors(cors -> cors.disable())
				.authorizeHttpRequests(auth -> auth.requestMatchers("/home/**").authenticated()
						.requestMatchers("/auth/login").permitAll().requestMatchers("api/user/login").permitAll()
						.requestMatchers("/api/user/userProfile").permitAll().requestMatchers("api/user/signup")
						.permitAll()
//						.requestMatchers("/api/user/updateProfile").permitAll()//secure
						.requestMatchers("/auth/create-user").permitAll()
//						.requestMatchers("/api/venue/createVenue").permitAll()//secure
						.requestMatchers("/api/venue/getAllVenues").permitAll()
						.requestMatchers("/api/venue/venueDetails").permitAll().requestMatchers("/api/venue/search")
						.permitAll().anyRequest().authenticated())
				.exceptionHandling(ex -> ex.authenticationEntryPoint(point))
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

//	@Bean
//	public CorsFilter corsFilter() {
//		CorsConfiguration config = new CorsConfiguration();
//		config.addAllowedOrigin("http://localhost:3000"); // Allow requests from this origin
//		config.addAllowedMethod("*"); // Allow all HTTP methods
//		config.addAllowedHeader("*"); // Allow all headers
//		config.setAllowCredentials(true); // Allow credentials (e.g., cookies)
//
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**", config);
//
//		return new CorsFilter(source);
//	}
//	@Bean
//	public UserDetailsService getUserDetailService() {
//		return new CustomUserDetailService();
//	}

	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
		logger.info("SecurityConfig class--daoAuthenticationProvider()", provider);
		return provider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
		logger.info("SecurityConfig class--authenticationManager()", builder);

		return builder.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		logger.info("SecurityConfig class--passwordEncoder()");
		return new BCryptPasswordEncoder();
	}

}

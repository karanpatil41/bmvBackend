package com.bmv.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer{
//	@Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("http://localhost:3000") // Add your frontend URL here
//                .allowedMethods("GET", "POST", "PUT", "DELETE") // Add the HTTP methods you want to allow
//                .allowedHeaders("Authorization", "Content-Type") // Add the headers you want to allow
//                .allowCredentials(true); // Allow credentials (e.g., cookies)
//    }

}

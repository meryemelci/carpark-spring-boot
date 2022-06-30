//package com.automation.carpark.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//public class PageConfig implements WebMvcConfigurer {
//	
//	@Bean
//	public BCryptPasswordEncoder passwordEncoder() {
//	    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//	    return bCryptPasswordEncoder;
//	}
//	
//	@Override
//	public void addViewControllers(ViewControllerRegistry registry) {
//	    registry.addViewController("/index").setViewName("index");
//	    registry.addViewController("/").setViewName("index");
//	    registry.addViewController("/dashboard").setViewName("dashboard");
//	    registry.addViewController("/login").setViewName("login");
//	}
//	
//
//}

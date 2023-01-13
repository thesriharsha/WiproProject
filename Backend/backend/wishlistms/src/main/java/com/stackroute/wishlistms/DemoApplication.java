package com.stackroute.wishlistms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 1) @Configuration
 * 2) @ComponentScan
 * 3)@EnableAutoConfiguration
 */
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);


	}
	@Bean
	public CorsFilter corsFilter(){
		UrlBasedCorsConfigurationSource src=new UrlBasedCorsConfigurationSource();
		CorsConfiguration config=new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedMethod("*");
		config.addAllowedHeader("*");
		config.addAllowedOrigin("http://localhost:4200");
		src.registerCorsConfiguration("/**",config);
		return new CorsFilter(src);
	}


}

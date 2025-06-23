package com.polyclinicapp.policlinico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan; 

@SpringBootApplication
@ComponentScan(basePackages = "com.polyclinicapp.policlinico.*") 
public class PoliclinicoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PoliclinicoApplication.class, args);
	}

}

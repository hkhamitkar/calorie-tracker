package com.capcal.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class CapcalUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CapcalUserServiceApplication.class, args);
	}

}

package com.lasershop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(scanBasePackages = "com.lasershop", exclude = {SecurityAutoConfiguration.class})
@EntityScan(basePackages = "com.lasershop.models")
public class LasershopApplication {

	public static void main(String[] args) {
		SpringApplication.run(LasershopApplication.class, args);
	}

}

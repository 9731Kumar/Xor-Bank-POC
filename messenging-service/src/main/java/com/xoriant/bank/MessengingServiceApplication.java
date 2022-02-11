package com.xoriant.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MessengingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessengingServiceApplication.class, args);
	}

}

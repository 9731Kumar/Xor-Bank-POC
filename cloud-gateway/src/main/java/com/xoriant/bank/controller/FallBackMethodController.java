package com.xoriant.bank.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class FallBackMethodController {
	
	@RequestMapping("/managerServiceFallBack")
	public Mono<String> managerServiceFallBack() {
		return Mono.just( "Manager Service taking than Expected time, Please try again later");
	}
	
	@RequestMapping("/customerServiceFallBack")
	public Mono<String> customerServiceFallBack() {
		return Mono.just( "Customer Service taking than Expected time, Please try again later");
	}

}

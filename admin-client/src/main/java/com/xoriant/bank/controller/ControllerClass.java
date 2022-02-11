package com.xoriant.bank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class ControllerClass {
	
	
	@GetMapping
	public String getStatus() {
		return "It's Working fine.........!";
	}

}

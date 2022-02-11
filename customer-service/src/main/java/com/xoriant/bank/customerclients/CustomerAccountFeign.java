package com.xoriant.bank.customerclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="ACCOUNT-SERVICE")
public interface CustomerAccountFeign {
	
	@GetMapping("/account/{accountNumber}")
	public Double getBalanceByAccountNumber(@PathVariable long accountNumber);

}

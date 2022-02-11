package com.xoriant.bank.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.xoriant.bank.entity.Transaction;

@FeignClient(name="TRANSACTION-SERVICE")
public interface TransactionFeign {
	
	@PostMapping("/transaction/deposit")
	public String depositTheAmount(@RequestBody Transaction transaction);
	
	@PostMapping("/transaction/withdraw")
	public Transaction withdrawTheAmount(@RequestBody Transaction transaction);
	
	@PostMapping("/transaction/fundTransfer")
	public Transaction fundTransfer(@RequestBody Transaction transaction);

}

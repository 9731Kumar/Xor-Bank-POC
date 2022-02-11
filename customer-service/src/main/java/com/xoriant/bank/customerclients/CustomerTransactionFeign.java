package com.xoriant.bank.customerclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.xoriant.bank.entity.Transaction;

@FeignClient(name="TRANSACTION-SERVICE")
public interface CustomerTransactionFeign {
	
	@PostMapping("/transaction/{accountNumber}")
	public Transaction doTransaction(@RequestBody Transaction transaction, @PathVariable long accountNumber);

}

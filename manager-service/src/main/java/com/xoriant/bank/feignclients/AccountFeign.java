package com.xoriant.bank.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.xoriant.bank.dto.AccountDTO;
import com.xoriant.bank.entity.Account;


@FeignClient(name="ACCOUNT-SERVICE")
public interface AccountFeign {
	
	@PostMapping("/account/save")
	public Account addAccount(@RequestBody AccountDTO accountDto);
	
	@PutMapping("/account/update")
	public Account updateAccount(@RequestBody AccountDTO accountDto);
	
	@DeleteMapping("/account/delete/{accountNumber}")
	public void deleteAccount(@PathVariable long accountNumber);
	
 
	@GetMapping("/account/balanceByManager/{managerEmail}/{accountNumber}")
	public ResponseEntity<?> getBalanceByManager1(@PathVariable String managerEmail,@PathVariable long accountNumber);

}

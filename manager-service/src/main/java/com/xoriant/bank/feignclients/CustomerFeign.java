package com.xoriant.bank.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.xoriant.bank.entity.Customer;

@FeignClient(name="CUSTOMER-SERVICE")
public interface CustomerFeign {
	
	@PostMapping("/api/customer/saveCustomer/{email}")
	public Customer saveCustomer(@RequestBody Customer customer,@PathVariable String email);
	
	@PutMapping("/api/customer/updateCustomer/{email}")
	public Customer updateCustomer(@RequestBody Customer customer,@PathVariable String email);
	
	

}

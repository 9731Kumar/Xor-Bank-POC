package com.xoriant.bank.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.xoriant.bank.entity.Transaction;

@FeignClient(name="REPORTS-SERVICE")
public interface ReportFeign {
	
	@GetMapping("/report/miniStatement/{accountNumber}")
	public List<Transaction> getMiniStatementForAccount(@PathVariable long accountNumber );
	
	@GetMapping("/report/customizedStatement/{accountNumber}/{from}/{to}")
	public List<Transaction> getCustomizedStatement(@PathVariable long accountNumber,@PathVariable String from,@PathVariable String to);

}

package com.xoriant.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.xoriant.bank.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	@Modifying
	@Query("delete from Customer c where c.emailId= :customerEmail")
	void deleteByEmailId(String customerEmail);

	Customer findByEmailId(String customerEmail);

	

}

package com.xoriant.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xoriant.bank.entity.Account;
import com.xoriant.bank.entity.Customer;

public interface AccountRepository extends JpaRepository<Account, Long> {

	List<Customer> findByCustomer_PersonIdEquals(int id);

}

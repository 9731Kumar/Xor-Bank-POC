package com.xoriant.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xoriant.bank.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

	Account findByCustomer_PersonIdEquals(int id);

	

}

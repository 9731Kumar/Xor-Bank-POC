package com.xoriant.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xoriant.bank.entity.Account;

public interface AccountRepo extends JpaRepository<Account, Long> {

	Account findByAccountNumber(long accountNumber);

	void deleteByAccountNumber(long accountNumber);

}

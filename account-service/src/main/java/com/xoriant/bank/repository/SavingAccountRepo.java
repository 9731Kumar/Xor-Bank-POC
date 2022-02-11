package com.xoriant.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.xoriant.bank.entity.SavingAccount;

public interface SavingAccountRepo extends JpaRepository<SavingAccount, Long> {

	SavingAccount findByAccountNumber(long accountNumber);

	@Modifying
	@Query("delete from SavingAccount s where accountNumber= :accountNumber")
	void deleteByAccountNumber(long accountNumber);

}

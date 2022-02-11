package com.xoriant.bank.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.xoriant.bank.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

	
	@Query(value = "from Transaction t where t.fromAccount.accountNumber = ?1 or t.toAccount.accountNumber = ?1 ")
	//@Query(nativeQuery = true, value = "from Transaction where fromAccount=?1")
	List<Transaction> findByAccountNumbers(long accountNumber);

	

	

	@Query(nativeQuery = true, value="Select * FROM transactions  WHERE  transaction_date BETWEEN :fromDate AND :toDate"
			+ " and from_account_number = :accountNumber or to_account_number=:accountNumber")
	List<Transaction> findByDates(long accountNumber, LocalDate fromDate, LocalDate toDate);





	

}

package com.xoriant.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xoriant.bank.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

}

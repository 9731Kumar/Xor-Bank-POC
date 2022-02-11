package com.xoriant.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xoriant.bank.entity.CurrentAccount;

public interface CurrentAccountRepo extends JpaRepository<CurrentAccount, Long> {

}

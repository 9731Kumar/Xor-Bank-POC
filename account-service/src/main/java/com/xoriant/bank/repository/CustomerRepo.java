package com.xoriant.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xoriant.bank.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {

}

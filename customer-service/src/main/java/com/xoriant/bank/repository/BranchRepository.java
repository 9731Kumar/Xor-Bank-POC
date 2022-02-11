package com.xoriant.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xoriant.bank.entity.Branch;

public interface BranchRepository extends JpaRepository<Branch, Integer> {

}

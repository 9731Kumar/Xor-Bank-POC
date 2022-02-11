package com.xoriant.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xoriant.bank.entity.Manager;

public interface ManagerRepo extends JpaRepository<Manager, Integer> {

	Manager findByEmailId(String managerEmail);

}

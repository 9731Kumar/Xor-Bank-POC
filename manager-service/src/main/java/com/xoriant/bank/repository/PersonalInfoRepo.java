package com.xoriant.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xoriant.bank.entity.PersonalInfo;

public interface PersonalInfoRepo extends JpaRepository<PersonalInfo, Integer> {

	PersonalInfo getByEmailId(String managerEmail);

}

package com.xoriant.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xoriant.bank.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	User findByUserName(String userName);

}

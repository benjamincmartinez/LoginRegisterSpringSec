package com.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springsecurity.entity.User;

public interface UserRepository extends JpaRepository<User,Long>{
	User findByEmail(String email);
}

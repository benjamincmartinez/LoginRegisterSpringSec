package com.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springsecurity.entity.Role;

public interface RoleRepository extends JpaRepository<Role,Long>{
	Role findByName(String name);
}

package com.springsecurity.service;

import java.util.List;

import com.springsecurity.dto.UserDto;
import com.springsecurity.entity.User;

public interface UserService {
	void saveUser(UserDto userDto);
	User findUserByEmail(String email);
	
	List<UserDto> findAllUsers();
}

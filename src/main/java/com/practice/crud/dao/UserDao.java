package com.practice.crud.dao;

import java.util.List;

import org.springframework.data.domain.Page;

import com.practice.crud.models.User;
import com.practice.crud.request.dto.UserDto;

public interface UserDao { 
	 User save(User user);

	User getByUserName(String userName);

	long update(UserDto dto);

	Page<User> getAll(int page, int size);

	Page<User> getAllByUserName(String id,int page,int size);

	List<User> getAll();

	List<User> getAllByUserName(String userName);

	User getByUserId(String id);

	void deleteById(String id);

	List<User> getAllByAddress(String key, boolean order);
		


}

package com.practice.crud.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.practice.crud.dao.UserDao;
import com.practice.crud.models.User;
import com.practice.crud.request.dto.LoginDto;
import com.practice.crud.request.dto.UserDto;
import com.practice.crud.response.dto.LoginResponse;
import com.practice.crud.response.dto.UserResponse;
import com.practice.crud.responses.Paginate;
import com.practice.crud.token.utils.TokenGeneration;

@Service
public class UserService {
	@Autowired
	UserDao dao;
	@Autowired
	TokenGeneration tokenGenerate;
	public User save(User user) throws Exception {
		User bean = dao.getByUserName(user.getUserName());
		if(bean !=null) {
			throw new Exception("User alredy exist"+"        "+user.getUserName()+"          ");
		}
		return dao.save(user);
	}

	public void update(UserDto dto) throws Exception {

		long count=dao.update(dto);
		if(count<=0) {
			throw new Exception("No user found");
		}
	}

	public LoginResponse login(LoginDto dto) throws Exception {
		User user= dao.getByUserName(dto.getUserName());
		if(user==null) {
			throw new Exception("No User Found"+dto.getUserName());
		}
		if((dto.getPassword().isEmpty()||dto.getPassword()==null)||(dto.getUserName().isEmpty()||dto.getUserName()==null)) {
			throw new Exception("Required fields are missing"+dto.getPassword()+dto.getUserName());
		}
		String token = tokenGenerate.generateToken(dto.getUserName(), dto.getPassword());
		String password=dto.getPassword();
		String userName=dto.getUserName();
		LoginResponse resp=null;
		if((password.equalsIgnoreCase(user.getPassword()))&&(userName.equalsIgnoreCase(user.getUserName()))) {
			resp=new LoginResponse();
			resp.setAddress(user.getAddress());
			resp.setContactNumber(user.getContactNumber());
			resp.setEmpId(user.getEmpId());
			resp.setSalary(user.getSalary());
			resp.setUserName(user.getUserName());
			resp.setToken(token);
		}
		return resp;
	}

	public Paginate<User> getAll(int page,int size) {
		
	Page<User> paginated = dao.getAll(page, size);
	paginated.getSort();
	Paginate<User> result=new Paginate<User>();
	result.setList(paginated.getContent());
	result.setSize(paginated.getSize());
	result.setTotalElements(paginated.getTotalElements());
	result.setTotalPages(paginated.getTotalPages());
	return result;
	
	}

	public Paginate<UserResponse> getByUserName(String id,int page,int size) throws Exception {
		Page<User> users = dao.getAllByUserName(id,page,size);
		if(users==null) {
			throw new Exception("User not found");
		}
		UserResponse response=null;
		List<UserResponse> resp=new ArrayList<UserResponse>();
		for (User user : users) {
			response=new UserResponse();
			response.setAddress(user.getAddress());
			response.setContactNumber(user.getContactNumber());
			response.setEmpId(user.getEmpId());
			response.setSalary(user.getSalary());
			response.setUserName(user.getUserName());
			response.setAadharNumber(user.getAadharNumber());
			response.setEnrolledAt(user.getCreatedAt());
			resp.add(response);
		}
		Paginate<UserResponse> result=new Paginate<UserResponse>();
		result.setList(resp);
		result.setSize(users.getSize());
		result.setTotalElements(users.getTotalElements());
		result.setTotalPages(users.getTotalPages());
		return result;
	}

	public List<User> getAll() {
		return dao.getAll();
	}

	public List<UserResponse> getByUserName(String userName) throws Exception {
		List<User> users = dao.getAllByUserName(userName);
		if(users==null) {
			throw new Exception("User not found");
		}
		UserResponse response=null;
		List<UserResponse> resp=new ArrayList<UserResponse>();
		for (User user : users) {
			response=new UserResponse();
			response.setAddress(user.getAddress());
			response.setContactNumber(user.getContactNumber());
			response.setEmpId(user.getEmpId());
			response.setSalary(user.getSalary());
			response.setUserName(user.getUserName());
			response.setAadharNumber(user.getAadharNumber());
			resp.add(response);
		}
		return resp;
	}

	public User getById(String id) {
		User user=dao.getByUserId(id);
		return user;
	}

	public void deleteById(String id) {
		dao.deleteById(id);		
	}

	public List<UserResponse> getAllByAddress(String key, boolean order) {
		List<User> users=dao.getAllByAddress(key,order);
		UserResponse resp=null;
		List<UserResponse> response=new ArrayList<UserResponse>();
		for (User user : users) {
			resp=new UserResponse();
			resp.setAddress(user.getAddress());
			resp.setContactNumber(user.getContactNumber());
			resp.setEmpId(user.getEmpId());
			resp.setSalary(user.getSalary());
			resp.setUserName(user.getUserName());
			response.add(resp);
		}
		
		return response;
	}
	
}

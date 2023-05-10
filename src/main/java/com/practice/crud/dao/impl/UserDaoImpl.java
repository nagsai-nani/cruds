 package com.practice.crud.dao.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import com.mongodb.client.result.UpdateResult;
import com.practice.crud.dao.UserDao;
import com.practice.crud.models.User;
import com.practice.crud.repository.UserRepository;
import com.practice.crud.request.dto.UserDto;
@Repository
public class UserDaoImpl implements UserDao{
	
@Autowired MongoTemplate temp;
@Autowired UserRepository repo;
	@Override
	public User save(User user) {
		return repo.save(user);
	}
	@Override
	public User getByUserName(String aadharNumber) {
		Query query = new Query();
		query.addCriteria(Criteria.where("aadharNumber").is(aadharNumber));
		return temp.findOne(query, User.class);
	}
	@Override
	public long update(UserDto dto) {
		Query query =new Query();
		query.addCriteria(Criteria.where("userName").is(dto.getUserName()));
		Update update =new Update();
		if(dto.getPassword()!=null) {
			update.set("password", dto.getPassword());
		}
		if(dto.getContactNumber()!=null) {
			update.set("contactNumber", dto.getContactNumber());
		}
	UpdateResult rs= temp.updateFirst(query,update,User.class);
	return rs.getModifiedCount();
	}
	@Override
	public Page<User> getAll(int page, int size) {
		final Pageable pageble= (Pageable) PageRequest.of(page - 1, size);
		Query query =new Query();
		query.with(pageble);
		List<User> list =temp.find(query, User.class);
		return PageableExecutionUtils.getPage(list, pageble,() -> temp.count(query.limit(-1).skip(-1), User.class));
	}
	@Override
	public Page<User> getAllByUserName(String id,int page,int size) {
		final Pageable pageble= (Pageable) PageRequest.of(page - 1, size);
		Query query =new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		query.with(pageble);
		List<User> list =temp.find(query, User.class);
		return PageableExecutionUtils.getPage(list, pageble,() -> temp.count(query.limit(-1).skip(-1), User.class));
	}
	@Override
	public List<User> getAll() {
	
		return temp.findAll(User.class);
	}
	@Override
	public List<User> getAllByUserName(String userName) {
		Query query = new Query();
		query.addCriteria(Criteria.where("userName").is(userName));
		return temp.find(query, User.class);
	}
	@Override
	public User getByUserId(String id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		return temp.findOne(query, User.class);
	}
	@Override
	public void deleteById(String id) {
		Query query =new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		repo.deleteById(id);
	}
	@Override
	public List<User> getAllByAddress(String key, boolean order) {
		Query query=new Query();
		query.addCriteria(Criteria.where("deleted").is(false));
		if(key!=null) {
			if(order==true) {
			query.with(Sort.by(Sort.Direction.ASC, key));
			}
			else {
				query.with(Sort.by(Sort.Direction.DESC,key));
			}
		}
		
		return temp.find(query,User.class);
	}

}

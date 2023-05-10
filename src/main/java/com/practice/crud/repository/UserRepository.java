package com.practice.crud.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.practice.crud.models.User;

public interface UserRepository extends MongoRepository< User,String> {

}

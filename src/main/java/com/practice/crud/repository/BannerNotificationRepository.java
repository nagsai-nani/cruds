package com.practice.crud.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.practice.crud.models.BannerNotification;
public interface BannerNotificationRepository extends MongoRepository<BannerNotification,String>{

}

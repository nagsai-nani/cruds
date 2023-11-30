package com.practice.crud.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.practice.crud.dao.BannerNotificationDao;
import com.practice.crud.models.BannerNotification;
import com.practice.crud.repository.BannerNotificationRepository;
@Repository
public class BannerNotificationDaoImpl implements BannerNotificationDao {
@Autowired BannerNotificationRepository repo;
@Autowired MongoTemplate temp;
	@Override
	public BannerNotification save(BannerNotification bean) {
		return temp.save(bean);
	}

}

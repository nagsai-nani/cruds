package com.practice.crud.service;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.crud.dao.BannerNotificationDao;
import com.practice.crud.models.BannerNotification;
@Service

public class BannerNotificationService {
@Autowired
BannerNotificationDao dao;
	public BannerNotification save(BannerNotification bean) throws Exception {

		if(bean.getDescription().length()>200) {
			throw new Exception(" The length of the notification should not exceed the 200 characters");
			}
		if(bean.getDescription().length()<=9) {
			throw new Exception("The message should have minimum 10 characters");
		}
		
		return dao.save(bean);
	}

}

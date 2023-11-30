package com.practice.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.crud.models.BannerNotification;
import com.practice.crud.service.BannerNotificationService;
@RestController
@RequestMapping("/banner/notification")

public class BannerNotificationController {
	@Autowired
	BannerNotificationService service;
	
@PostMapping("/save")
public ResponseEntity<BannerNotification> save(BannerNotification bean) throws Exception{
	return ResponseEntity.ok(service.save(bean));
	} 
}

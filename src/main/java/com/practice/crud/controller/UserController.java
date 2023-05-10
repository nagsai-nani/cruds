package com.practice.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.practice.crud.models.User;
import com.practice.crud.request.dto.LoginDto;
import com.practice.crud.request.dto.UserDto;
import com.practice.crud.response.dto.LoginResponse;
import com.practice.crud.response.dto.UserResponse;
import com.practice.crud.responses.Paginate;
//import com.practice.crud.roughwork.RoughWork;
import com.practice.crud.service.UserService; 
@RestController
@RequestMapping("/rest/user")
public class UserController {
	@Autowired
	UserService service;
//	
//	@Autowired RoughWork work;
	
@PostMapping("/save")
public ResponseEntity<User> saveUser(@RequestBody User user) throws Exception{
	return ResponseEntity.ok(service.save(user));
	
	}
@PutMapping("/update")
public ResponseEntity<String> update(@RequestBody UserDto dto) throws Exception{
	service.update(dto);
	return ResponseEntity.ok("Updated");
	}
@PostMapping("/login")
public ResponseEntity<LoginResponse> login(@RequestBody LoginDto dto) throws Exception{
	return ResponseEntity.ok(service.login(dto));
}
//@PostMapping("/saved")
//public ResponseEntity<String> saveUsers(User user){
////work.save( user);
//	return ResponseEntity.ok("Saved");
//}
@GetMapping("/get/all")
public ResponseEntity<Paginate<User>> getAll(@RequestParam("page")int page,@RequestParam("size")int size){
	return ResponseEntity.ok(service.getAll(page,size));
}
@GetMapping("/username/{id}")
public ResponseEntity<Paginate<UserResponse>> getByUserName(@PathVariable String id,@RequestParam("page")int page,@RequestParam("size")int size) throws Exception{
	return ResponseEntity.ok(service.getByUserName(id,page,size));
	
}
@GetMapping("/get/{userName}")
public List<UserResponse> getByUserName(@PathVariable String userName) throws Exception{
	return service.getByUserName(userName);
	
}
@GetMapping("/getall")
public ResponseEntity<List<User>> getAll() throws Exception{
	return ResponseEntity.ok(service.getAll());
	
}
@GetMapping("/get/id/{id}")
public ResponseEntity<User> getById(@PathVariable String id){
	return ResponseEntity.ok(service.getById(id));
	
}
@DeleteMapping("/delete/{id}")
public ResponseEntity<String> deleteById(@PathVariable String id){
	service.deleteById(id);
	return ResponseEntity.ok("deleted");
	
}

@GetMapping("/get/all/address")
public ResponseEntity<List<UserResponse>> getAllByAddress(@RequestParam(value = "key",required = false) String key ,@RequestParam(value="order",required = false) boolean order){
	return ResponseEntity.ok(service.getAllByAddress(key,order));
	
}


}


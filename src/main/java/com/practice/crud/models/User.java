package com.practice.crud.models;

import org.springframework.boot.autoconfigure.domain.EntityScan;
@EntityScan(basePackageClasses = {})
public class User extends BaseModel {
	

private String aadharNumber;
private String userName ="harsha";
private String contactNumber;
private String address;
private String password;
private String salary;
private String empId;

public String getAadharNumber() {
	return aadharNumber;
}
public void setAadharNumber(String aadharNumber) {
	this.aadharNumber = aadharNumber;
}

public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getContactNumber() {
	return contactNumber;
}
public void setContactNumber(String contactNumber) {
	this.contactNumber = contactNumber;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getSalary() {
	return salary;
}
public void setSalary(String salary) {
	this.salary = salary;
}
public String getEmpId() {
	return empId;
}
public void setEmpId(String empId) {
	this.empId = empId;
}

}

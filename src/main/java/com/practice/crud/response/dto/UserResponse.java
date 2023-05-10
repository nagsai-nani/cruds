package com.practice.crud.response.dto;

import java.util.Date;

public class UserResponse {
	private String aadharNumber;
	private String userName;
	private String contactNumber;
	private String address;
	private String salary;
	private String empId;
	private Date enrolledAt;
	
	
	public Date getEnrolledAt() {
		return enrolledAt;
	}
	public void setEnrolledAt(Date enrolledAt) {
		this.enrolledAt = enrolledAt;
	}
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

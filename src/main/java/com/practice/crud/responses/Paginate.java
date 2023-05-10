package com.practice.crud.responses;

import java.util.List;

public class Paginate<T> {
	private List<T> list;
	private int size;
	private int totalPages;
	private long totalElements;
	
	public Paginate() {}
	
	
	public Paginate(List<T> list, int size, int totalPages, long totalElements) {
		super();
		this.list = list;
		this.size = size;
		this.totalPages = totalPages;
		this.totalElements = totalElements;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public long getTotalElements() {
		return totalElements;
	}
	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}
	

}

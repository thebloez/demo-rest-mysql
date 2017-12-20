package com.springboot.restmysql.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

public class SuccessRestResponse<T>  {

	private String code = ""+HttpStatus.OK.value();
	private String status = "success";
	private List<T> data;
	
	
	public SuccessRestResponse(List<T> data) {
		super();
		this.data = data;
	}
	
	public SuccessRestResponse(T data) {
		super();
		this.data = new ArrayList<>();
		this.data.add(data);
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
}

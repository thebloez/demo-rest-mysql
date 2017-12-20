package com.springboot.restmysql.rest;

import org.springframework.http.HttpStatus;

public class ErrorRestResponse {

	private String code = HttpStatus.BAD_REQUEST.name();
	private String status = "error";
	private String message = "";
	
	public ErrorRestResponse() {
		super();
	}
	
	public ErrorRestResponse(String message) {
		super();
		this.message = message;
	}

	public ErrorRestResponse(String code, String message) {
		super();
		this.code = code;
		this.message = message;
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
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	
}

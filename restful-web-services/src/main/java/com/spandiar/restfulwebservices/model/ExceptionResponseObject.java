package com.spandiar.restfulwebservices.model;

import java.time.LocalDate;

public class ExceptionResponseObject {
	
	private LocalDate datetime;
	private String errorCode;
	private String message;
	
	
	public ExceptionResponseObject() {
		super();
	}


	public ExceptionResponseObject(LocalDate datetime, String errorCode, String message) {
		super();
		this.datetime = datetime;
		this.errorCode = errorCode;
		this.message = message;
	}


	public LocalDate getDatetime() {
		return datetime;
	}


	public String getErrorCode() {
		return errorCode;
	}


	public String getMessage() {
		return message;
	}
	
}

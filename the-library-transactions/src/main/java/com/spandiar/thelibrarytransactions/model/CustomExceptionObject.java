package com.spandiar.thelibrarytransactions.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class CustomExceptionObject {
	
	private UUID transactionId;
	private LocalDateTime timeStamp;
	private String errorCode;
	private String errorMessage;
	
	public CustomExceptionObject() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomExceptionObject(UUID transactionId, LocalDateTime timeStamp, String errorCode, String errorMessage) {
		super();
		this.transactionId = transactionId;
		this.timeStamp = timeStamp;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public UUID getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(UUID transactionId) {
		this.transactionId = transactionId;
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}

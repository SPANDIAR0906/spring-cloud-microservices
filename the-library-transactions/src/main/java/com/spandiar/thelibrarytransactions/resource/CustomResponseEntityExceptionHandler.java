package com.spandiar.thelibrarytransactions.resource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.spandiar.thelibrarytransactions.customexceptions.BookNotFoundException;
import com.spandiar.thelibrarytransactions.customexceptions.EntityNotFoundException;
import com.spandiar.thelibrarytransactions.customexceptions.InvalidRequestException;
import com.spandiar.thelibrarytransactions.customexceptions.UserNameAlreadyExistsException;
import com.spandiar.thelibrarytransactions.model.CustomExceptionObject;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(UserNameAlreadyExistsException.class)
	public ResponseEntity<CustomExceptionObject> userNameAlreadyExists(UserNameAlreadyExistsException exception, WebRequest request){
		
		CustomExceptionObject exceptionObj = new CustomExceptionObject(
				UUID.randomUUID(),
				LocalDateTime.now(),
				"INVALID_USERNAME",
				exception.getMessage());
		
		return new ResponseEntity(exceptionObj, HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<CustomExceptionObject> entityNotFound(EntityNotFoundException exception, WebRequest request){
		
		CustomExceptionObject exceptionObj = new CustomExceptionObject(
				UUID.randomUUID(),
				LocalDateTime.now(),
				"ENTITY_NOT_FOUND",
				exception.getMessage());
		
		return new ResponseEntity(exceptionObj, HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(InvalidRequestException.class)
	public ResponseEntity<CustomExceptionObject> invalidRequestException(InvalidRequestException exception, WebRequest request){
		
		CustomExceptionObject exceptionObj = new CustomExceptionObject(
				UUID.randomUUID(),
				LocalDateTime.now(),
				"INVALID_REQUEST",
				exception.getMessage());
		
		return new ResponseEntity(exceptionObj, HttpStatus.BAD_REQUEST);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {

		CustomExceptionObject exceptionObj = new CustomExceptionObject(
				UUID.randomUUID(),
				LocalDateTime.now(),
				"INVALID_REQUEST_ARGUMENTS",
				exception.getMessage()
				);
		return new ResponseEntity(exceptionObj, HttpStatus.BAD_REQUEST);
	}
	
	
	
}

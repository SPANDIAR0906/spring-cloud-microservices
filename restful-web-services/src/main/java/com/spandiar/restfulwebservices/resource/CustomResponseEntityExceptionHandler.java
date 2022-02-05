package com.spandiar.restfulwebservices.resource;

import java.time.LocalDate;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.spandiar.restfulwebservices.model.ExceptionResponseObject;
import com.spandiar.restfulwebservices.resource.exceptions.BookNotFoundException;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	

	@ExceptionHandler(BookNotFoundException.class)
	public final ResponseEntity<ExceptionResponseObject>
		handleBookNotFoundException(BookNotFoundException ex, WebRequest request) 
				throws Exception {
		System.out.println("Inside handleBookNotFoundException");
		ExceptionResponseObject ero = new ExceptionResponseObject(LocalDate.now(), "BOOK_NOT_FOUND", ex.getMessage());
		return new ResponseEntity<ExceptionResponseObject>(ero, HttpStatus.NOT_FOUND);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		ExceptionResponseObject ero = new ExceptionResponseObject(LocalDate.now(), "INVALID_REQUEST_OBJECT", ex.getBindingResult().toString());
		return new ResponseEntity(ero, HttpStatus.BAD_REQUEST);
	}
	
	

}

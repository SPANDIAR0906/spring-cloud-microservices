package com.spandiar.thelibrarytransactions.resource;


import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spandiar.thelibrarytransactions.customexceptions.InvalidRequestException;
import com.spandiar.thelibrarytransactions.model.BookRatings;
import com.spandiar.thelibrarytransactions.model.BookRatingsRequest;
import com.spandiar.thelibrarytransactions.model.BookRatingsResponse;
import com.spandiar.thelibrarytransactions.model.UserBase;
import com.spandiar.thelibrarytransactions.model.UserBaseResponse;
import com.spandiar.thelibrarytransactions.service.UserService;

@RestController
@RequestMapping(path = "/transactions")
public class UserResource {
	
	@Autowired
	private UserService userService;
	
	private Logger logger = LoggerFactory.getLogger(UserResource.class);
	
	@PostMapping(path = "/user")
	public ResponseEntity<UserBase> createUser(@Valid @RequestBody UserBase user) {
		
		BodyBuilder response = ResponseEntity.ok();
		
		if(null != user) {
			UserBase createdUser = userService.createUser(user);
			if(null != createdUser && createdUser.getId() != null) {
				return response.body(createdUser);
			}
		} else {
			throw new InvalidRequestException("Invalid Request");
		}
		
		return response.build();
	}
	
	@PostMapping(path = "/user/rating")
	public ResponseEntity<BookRatingsResponse> addBookRating(@Valid @RequestBody BookRatingsRequest rating) {
		
		BookRatingsResponse bookRatingResponse = new BookRatingsResponse();
		ResponseEntity<BookRatingsResponse> response = new ResponseEntity<BookRatingsResponse>(bookRatingResponse, HttpStatus.CREATED);
		
		if(null != rating) {
			bookRatingResponse = userService.addBookRating(rating, bookRatingResponse);
		} else {
			throw new InvalidRequestException("Invalid Request");
		}
		
		return response;
	}
	
	
	@GetMapping(path = "/user/{userId}")
	public UserBaseResponse getUserInfo(@PathVariable String userId) {
		
		logger.info("Getting details of user -> {} ", userId);
		return userService.getUser(userId);
		
	}
	

}

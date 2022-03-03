package com.spandiar.thelibrarytransactions.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Applications;
import com.spandiar.thelibrarytransactions.connector.BookConnectorProxy;
import com.spandiar.thelibrarytransactions.customexceptions.BookNotFoundException;
import com.spandiar.thelibrarytransactions.customexceptions.EntityNotFoundException;
import com.spandiar.thelibrarytransactions.customexceptions.UserNameAlreadyExistsException;
import com.spandiar.thelibrarytransactions.model.Book;
import com.spandiar.thelibrarytransactions.model.BookRatings;
import com.spandiar.thelibrarytransactions.model.BookRatingsRequest;
import com.spandiar.thelibrarytransactions.model.BookRatingsResponse;
import com.spandiar.thelibrarytransactions.model.UserBase;
import com.spandiar.thelibrarytransactions.model.UserBaseResponse;
import com.spandiar.thelibrarytransactions.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private BookConnectorProxy bookProxy;
	
	@Autowired
	@LoadBalanced
	private RestTemplate restTemplate;
	
	@Autowired
	private EurekaClient discoveryClient;
	
	private Logger logger = LoggerFactory.getLogger(UserService.class);
	
	static final String RESTFUL_WEB_SERVICES_ROOT = "http://localhost:8350/";
	static final String RESTFUL_WEB_SERVICES_SERVICE_DISCOVERY_ROOT = "http://the-library-backend/";
	static final String GET_BOOK_INFO = "library/book/";
	
	
	private String getLibraryBackendUrl(){
		
		Applications applications = discoveryClient.getApplications();
		InstanceInfo instance = discoveryClient.getNextServerFromEureka("THE-LIBRARY-BACKEND", false);
		return instance.getHomePageUrl();
		
	}
	
	
	public UserBase createUser(UserBase user) {
		
		// check if userName is available
		if(userRepo.isUserNameAvailable(user.getUserName())) {
			UserBase createdUser = userRepo.createUser(user);
			return createdUser;
		} else {
			throw new UserNameAlreadyExistsException("Username is taken already. Please choose a different name");
		}
		
	}
	
	public UserBaseResponse getUser(String userId) {
		
		UserBaseResponse userBaseResponse = new UserBaseResponse();
		
		if(null != userId) {
			 UserBase userInfoFromDB = userRepo.getUserInfo(userId);
			 
			 if(null != userInfoFromDB) {
				 // populate response object
				 if(null != userInfoFromDB) {
					 userBaseResponse.setUserName(userInfoFromDB.getUserName());
					 userBaseResponse.setFirstName(userInfoFromDB.getFirstName());
					 userBaseResponse.setLastName(userInfoFromDB.getLastName());
					 
					 List<BookRatings> ratingsFromDB = userInfoFromDB.getRatings();
					 List<BookRatingsResponse> bookRatingsResponse = new ArrayList<>();
					 
					 for(BookRatings dbRating : ratingsFromDB) {
						 
						 BookRatingsResponse ratingResponse = new BookRatingsResponse();
						 ratingResponse.setBookId(dbRating.getBookId());
//						 Book bookInfo = bookProxy.getBookInfo(dbRating.getBookId());
						 Book bookInfo = getBookInfoUsingId(dbRating.getBookId());
						 if(null != bookInfo) {
							 ratingResponse.setBookName(bookInfo.getBookName());
							 ratingResponse.setAuthor(bookInfo.getAuthor());
						 }
						 ratingResponse.setBookRating(dbRating.getBookRating());
						 ratingResponse.setComments(dbRating.getComments());
						 bookRatingsResponse.add(ratingResponse);
						 
					 }
					 userBaseResponse.setRatings(bookRatingsResponse);
				 }
			 } else {
				 throw new EntityNotFoundException("UserId being requested does not exist");
			 }
			 
		} else 
			throw new EntityNotFoundException("UserId cannot be empty for getting the userInfo");
		
		return userBaseResponse;
	}

	private Book getBookInfoUsingId(String bookId) {
//		String homePageURL = getLibraryBackendUrl();
//		logger.info("Received URl from EurekaClient -> {} ", homePageURL);
		ResponseEntity<Book> responseEntity = restTemplate.getForEntity(RESTFUL_WEB_SERVICES_SERVICE_DISCOVERY_ROOT +  GET_BOOK_INFO + bookId, Book.class);
		Book bookInfo = responseEntity.getBody();
		return bookInfo;
	}
	
	public BookRatingsResponse addBookRating(BookRatingsRequest rating, BookRatingsResponse bookRatingResponse) {
		
		Book bookInfo = new Book();
		
		// check if Book exists
		if(null != rating.getBookId()) {
			logger.info("Calling bookProxy to fetch book details");
			try {
//				 bookInfo = bookProxy.getBookInfo(rating.getBookId());
				 bookInfo = getBookInfoUsingId(rating.getBookId());
			} catch (Exception ex) {
				throw new EntityNotFoundException("Ratings being added for a book that does not exist");
			}
		}
		
		//construct a BookRating Object 
		if(null != rating.getUserName() && userRepo.doesUserExist(rating.getUserName())) {
			
			UserBase userInfo = userRepo.getUserInfo(rating.getUserName());
			BookRatings bookRatings = new BookRatings(userInfo, rating.getBookId(), rating.getBookRating(), rating.getComments());
			BookRatings bookRatingsFromDB = userRepo.addBookRatingForUser(bookRatings);
			logger.info("Ratings are added to the backend");
			
			// Construct the response object
			bookRatingResponse.setId(bookRatingsFromDB.getId());
			bookRatingResponse.setBookId(bookRatingsFromDB.getBookId());
			bookRatingResponse.setUserName(rating.getUserName());
			bookRatingResponse.setAuthor(bookInfo.getAuthor());
			bookRatingResponse.setBookName(bookInfo.getBookName());
			bookRatingResponse.setBookRating(bookRatingsFromDB.getBookRating());
			bookRatingResponse.setComments(bookRatingsFromDB.getComments());
		} else {
			throw new EntityNotFoundException("Ratings being added for an user that does not exist");
		}
		
		return bookRatingResponse;
		
	}
	

}

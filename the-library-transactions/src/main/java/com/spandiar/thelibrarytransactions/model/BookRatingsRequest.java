package com.spandiar.thelibrarytransactions.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;


public class BookRatingsRequest {
	
	private String userName;
	private String bookId;
	private float bookRating;
	private String comments;
	
	public BookRatingsRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUserName() {
		return userName;
	}

	public String getBookId() {
		return bookId;
	}

	public float getBookRating() {
		return bookRating;
	}

	public String getComments() {
		return comments;
	}
	
}

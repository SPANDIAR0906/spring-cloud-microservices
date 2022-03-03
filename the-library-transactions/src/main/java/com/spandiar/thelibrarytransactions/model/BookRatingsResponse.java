package com.spandiar.thelibrarytransactions.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public class BookRatingsResponse {
	
	@JsonIgnore
	private String id;
	private String userName;
	private String bookId;
	private String bookName;
	private String author;
	private float bookRating;
	private String comments;
	
	public BookRatingsResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public BookRatingsResponse(String id, String userName, String bookId, String bookName, String author,
			float bookRating, String comments) {
		super();
		this.id = id;
		this.userName = userName;
		this.bookId = bookId;
		this.bookName = bookName;
		this.author = author;
		this.bookRating = bookRating;
		this.comments = comments;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public float getBookRating() {
		return bookRating;
	}

	public void setBookRating(float bookRating) {
		this.bookRating = bookRating;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}

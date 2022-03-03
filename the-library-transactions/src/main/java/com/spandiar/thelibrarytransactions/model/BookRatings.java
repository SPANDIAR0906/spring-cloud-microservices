package com.spandiar.thelibrarytransactions.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class BookRatings {
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@JsonIgnore
	private String id;
	@ManyToOne
	@JoinColumn(name = "USER_BASE_UUID")
	@JsonBackReference
	private UserBase userBaseUUID;
	private String bookId;
	private float bookRating;
	private String comments;
	
	public BookRatings() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public BookRatings(UserBase userBaseUUID, String bookId, float bookRating, String comments) {
		super();
		this.userBaseUUID = userBaseUUID;
		this.bookId = bookId;
		this.bookRating = bookRating;
		this.comments = comments;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public UserBase getUserBaseUUID() {
		return userBaseUUID;
	}

	public void setUserBaseUUID(UserBase userBaseUUID) {
		this.userBaseUUID = userBaseUUID;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
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
	
}

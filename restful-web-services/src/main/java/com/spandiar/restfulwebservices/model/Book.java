package com.spandiar.restfulwebservices.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Book {
	
	@Id
	@Column(name = "BOOK_ID")
	private int bookId;
	@Size(min = 5, message = "bookName should atleast be of size 5 chars")
	private String bookName;
	@Size(min = 5, max = 30, message = "author name should be within 5 and 30 chars")
	private String author;
	@Size(min = 5, max = 30, message = "publisher name should be within 5 and 30 chars")
	private String publisher;
	@Size(max = 3, message = "langCd cannot be more than 3 characters")
	private String langCd;
//	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "book")
	@OneToMany(mappedBy = "book")
	private List<BookGenre> genres = new ArrayList<>();
	private LocalDate createdDate;
	
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Book(int bookId, String bookName, String author, String langCd, LocalDate createdDate) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.author = author;
		this.langCd = langCd;
		this.createdDate = createdDate;
	}
	
	public Book(int bookId, @Size(min = 5, message = "bookName should atleast be of size 5 chars") String bookName,
			@Size(min = 5, max = 30, message = "author name should be within 5 and 30 chars") String author,
			@Size(min = 5, max = 30, message = "publisher name should be within 5 and 30 chars") String publisher,
			@Size(max = 3, message = "langCd cannot be more than 3 characters") String langCd, List<BookGenre> genre,
			LocalDate createdDate) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.author = author;
		this.publisher = publisher;
		this.langCd = langCd;
		this.genres = genre;
		this.createdDate = createdDate;
	}

	
	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	@JsonManagedReference
	public List<BookGenre> getGenres() {
		return genres;
	}
	
	public void setGenres(List<BookGenre> genre) {
		this.genres = genre;
	}
	
	public void addGenre(BookGenre genre) {
		this.getGenres().add(genre);
	}

	public int getBookId() {
		return bookId;
	}


	public void setBookId(int bookId) {
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


	public String getLangCd() {
		return langCd;
	}

	public void setLangCd(String langCd) {
		this.langCd = langCd;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}
	
}

package com.spandiar.restfulwebservices.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class BookGenre {
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	private int genreId;
	@Enumerated(EnumType.STRING)
	private Genre genre;
	@ManyToOne
	@JoinColumn(name = "BOOK_ID")
	@JsonIgnore
	private Book book;
	@JsonIgnore
	private LocalDate createdDate;
	
	
	public BookGenre() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getGenreId() {
		return genreId;
	}

	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}

	public void setId(String id) {
		this.id = id;
	}

	@JsonBackReference
	public Book getBook() {
		return book;
	}


	public void setBook(Book book) {
		this.book = book;
	}

	public Genre getGenre() {
		return genre;
	}


	public void setGenre(Genre genre) {
		this.genre = genre;
	}


	public LocalDate getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}
	
}

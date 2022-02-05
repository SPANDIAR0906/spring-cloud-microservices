package com.spandiar.restfulwebservices.dao;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spandiar.restfulwebservices.model.Book;
import com.spandiar.restfulwebservices.model.BookGenre;
import com.spandiar.restfulwebservices.repository.BookRepository;
import com.spandiar.restfulwebservices.resource.exceptions.BookNotFoundException;

@Component
public class BookDao {
	
	@Autowired
	private BookRepository bookRepo;
	private static Random random = new Random();
	private Logger logger = LoggerFactory.getLogger(BookDao.class);
	
	public Book getBookById(int id){
		
		return bookRepo.getBookUsingId(id);
		
	}
	
	public List<Book> getAllBooks(){
		
		return bookRepo.getAllBooks();
		
	}
	
	public Book addBook(Book book) {
		
		int genreId = 0;
		
		int bookId = random.nextInt(1000);
		
		book.setBookId(bookId);
		book.setCreatedDate(LocalDate.now());
		
		if(!book.getGenres().isEmpty()) {
			
			for(BookGenre genre: book.getGenres()) {
				logger.info("Inside Book Genre -> {}", genreId);
				genre.setGenreId(++genreId);
				genre.setBook(book);
				genre.setCreatedDate(LocalDate.now());
			}
			
		}
		
		return bookRepo.addBook(book);
		
	}
	
	public void updateBook(Book book) {
		
		Book bookDetailsFromDB = getBookById(book.getBookId());
		
		if(null == bookDetailsFromDB) {
			throw new BookNotFoundException("The book you are trying to update is not available");
		}
		
		// bookName, author cannot be updated
		// update book publisher
		if(book.getPublisher() != null) {
			logger.info("Setting publisher");
			bookDetailsFromDB.setPublisher(book.getPublisher());
		}
		
		// update book genre
		if(!book.getGenres().isEmpty()) {
			
			logger.info("Updating bookGenre details");
			
			int genreId = 0;
			
			for(BookGenre genre : book.getGenres()) {
				genre.setGenreId(++genreId);
				genre.setBook(book);
				genre.setCreatedDate(LocalDate.now());
				bookDetailsFromDB.addGenre(genre);
			}
			
		}
		
		// merge book to the DB
		bookRepo.updateBook(bookDetailsFromDB);
		
	}
	
	public Book deleteBookById(int id) {
		
		Book bookToDelete = bookRepo.getBookUsingId(id);
		
		if(bookToDelete != null) {
			bookRepo.deleteBook(bookToDelete);
			return bookToDelete;
		} else {
			throw new BookNotFoundException(" Requested book is not found - id: " + id);
		}
		
	}

}

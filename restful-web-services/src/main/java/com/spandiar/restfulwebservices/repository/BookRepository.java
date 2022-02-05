package com.spandiar.restfulwebservices.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spandiar.restfulwebservices.model.Book;
import com.spandiar.restfulwebservices.model.BookGenre;

@Component
public class BookRepository {
	
	@Autowired
	private EntityManager entityManager;
	
	Logger logger = LoggerFactory.getLogger(BookRepository.class);
	
	public Book getBookUsingId(int id) {
		
		return entityManager.find(Book.class, id);
	}
	
	public List<Book> getAllBooks(){
		
		Query getAllBooks = entityManager.createNativeQuery("select * from book", Book.class);
		return getAllBooks.getResultList();
		
	}
	
	@Transactional
	public Book addBook(Book book) {
		
		entityManager.persist(book);
		for(BookGenre genre: book.getGenres()) {
			entityManager.persist(genre);
		}
		return book;
	}
	
	@Transactional
	public void updateBook(Book book) {
		
		logger.info("inside updateBook - {}", book.getBookName());

		for(BookGenre genre: book.getGenres()) {
//			logger.info("inside updateBook - Genres {} {} {}", genre.getBook().getBookId(), genre.getGenreId(), genre.getGenre());
			entityManager.persist(genre);
		}
		
		entityManager.merge(book);
	}
	
	@Transactional
	public void deleteBook(Book book) {
		
		// remove entries from the child record
		if(book.getGenres() != null) {
			for(BookGenre genre : book.getGenres()) {
				entityManager.remove(genre);
			}
		}
		
		entityManager.remove(book);
		
	}
	
}

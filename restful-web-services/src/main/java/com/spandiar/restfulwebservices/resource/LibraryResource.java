package com.spandiar.restfulwebservices.resource;

import java.net.URI;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.spandiar.restfulwebservices.dao.BookDao;
import com.spandiar.restfulwebservices.model.About;
import com.spandiar.restfulwebservices.model.Book;
import com.spandiar.restfulwebservices.resource.exceptions.BookNotFoundException;

@RestController
@RequestMapping("/library")
public class LibraryResource {
	
	@Autowired
	private BookDao bookDao;
	
	@Autowired
	private MessageSource messageSource;
	
	private Logger logger = LoggerFactory.getLogger(LibraryResource.class);
	
	
	/*
	 * method uses springboot's inbuilt internationalization support features
	 * refer to the messages.properties file for the keys and the values for each locale
	 * */
	
	@GetMapping(path = "/about")
	public About about() {

		logger.info("inside /about. value of Locale is -> {} ", LocaleContextHolder.getLocale());
		return aboutTheLibraryBuilder();
	}

	protected About aboutTheLibraryBuilder() {
		String heading = messageSource.getMessage("about.the.application.heading", null, "library", LocaleContextHolder.getLocale());
		String body = messageSource.getMessage("about.the.application.body", null, "library", LocaleContextHolder.getLocale());
		return new About(heading, body);
	}
	
	/*
	 * method is enhanced to return links to other relevent restapi's
	 * using the hateoas framework
	 * */
	
	@GetMapping(path = "/book/{id}")
	public EntityModel<Book> getBookUsingId(@PathVariable int id) {
		
		Book book = bookDao.getBookById(id);
		
		if(book != null) {
			EntityModel<Book> model = EntityModel.of(book);
			
			WebMvcLinkBuilder linkToAllUsers = 
					WebMvcLinkBuilder
						.linkTo(
								WebMvcLinkBuilder
									.methodOn(this.getClass()).getAllBooks());
			
			model.add(linkToAllUsers.withRel("all-books"));
			
			return model;
		} else {
			throw new BookNotFoundException(" Requested book is not found - id: " + id);
		}
		
	}
	
	
	@GetMapping(path = "/books")
	public List<Book> getAllBooks(){
		
		return bookDao.getAllBooks();
		
	}
	
	@PostMapping(path = "/book")
	public ResponseEntity<Book> addBook(@Valid @RequestBody Book book) {
		
		Book addedBook = bookDao.addBook(book);
		
		// Return 201 as part of the http response for the POST request
		// Response header - location will contain the link to the added book
		
		URI location = ServletUriComponentsBuilder
						.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(addedBook.getBookId())
						.toUri();
		
		return ResponseEntity.created(location).build();
		
	}
	
	@PutMapping(path = "/book")
	public void updateBookDetails(@RequestBody Book book) {
		
		bookDao.updateBook(book);
		
	}
	
	@DeleteMapping(path = "/book/{id}")
	public void deleteBookById(@PathVariable int id) {
		
		bookDao.deleteBookById(id);
		
	}

}

package com.jlcindia.booksearch;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@CrossOrigin
@RestController
public class BookSearchController {
	
	static Logger log = LoggerFactory.getLogger(BookSearchController.class);
	
	@GetMapping("/allbooks")
	@HystrixCommand(fallbackMethod = "getAllBooksFallback")
	public List<String> getAllBooks() {
		log.info("---BookSearchController---getAllBooks()---");
		// Success Case
		List<String> booksList = new ArrayList<String>();
		booksList.add("Java");
		booksList.add("Spring");
		booksList.add("Spring Boot");
		booksList.add("Angular");
		booksList.add("React");
		return booksList;
	}

	@GetMapping("/booksByAuthor/{author}")
	@HystrixCommand(fallbackMethod = "getBooksByAuthorFallback")
	public List<String> getBooksByAuthor(@PathVariable String author) {
		log.info("---BookSearchController---getBooksByAuthor()---");
		// Problem Case
		if (1 == 1) {
			throw new ArithmeticException();
		}
		return null;
	}

	public List<String> getAllBooksFallback() {
		log.info("---BookSearchController---getAllBooksFallback()---");
		List<String> booksList = new ArrayList<String>();
		booksList.add("No Books Available Currently");
		return booksList;
	}

	public List<String> getBooksByAuthorFallback(String author) {
		log.info("---BookSearchController---getBooksByAuthorFallback()--");
		List<String> booksList = new ArrayList<String>();
		booksList.add("No Books Available Currently for Author : " + author);
		return booksList;
	}

}


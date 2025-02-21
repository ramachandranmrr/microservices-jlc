package com.jlcindia.booksearch;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class BookSearchController {
	
	static Logger log = LoggerFactory.getLogger(BookSearchController.class);
	
	@GetMapping("/jlcbooks")
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
	
}


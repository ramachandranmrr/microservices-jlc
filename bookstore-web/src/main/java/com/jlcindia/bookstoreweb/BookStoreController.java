package com.jlcindia.bookstoreweb;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookStoreController {
	
	static Logger log = LoggerFactory.getLogger(BookStoreController.class);

	@Autowired
	BookSearchProxy bookSearchProxy;

	@GetMapping("/mybooks")
	public List<String> getMyBooks() {
		log.info("---BookStoreController---getMyBooks()---");
		List<String> booksList = bookSearchProxy.getAllBooks();
		return booksList;
	}

}

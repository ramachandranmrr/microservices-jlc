package com.jlcindia.booksearch;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

@CrossOrigin
@RestController
public class BookSearchController {

	static Logger log = LoggerFactory.getLogger(BookSearchController.class);
	@Autowired
	BookService bookService;

	@Operation(summary = "Retrieve a book by Id", description = "Get a book object by specifying its id. The response is book object with id, title, description and published status.")
	@GetMapping("/mybooks/{author}/{category}")
	// @ApiOperation(value = " getBooks", response = List.class, notes = "Returns
	// List of Books for given Author and Category")
	public List<Book> getBooks(@PathVariable String author, @PathVariable String category) {
		log.info("---BookController---getBooks()-----");
		System.out.println(author + "\t" + category);
		return bookService.getBooks(author, category);
	}

	@GetMapping("/mybook/{bookId}")
	// @ApiOperation(value = " getBookById", response = BookInfo.class, notes =
	// "Returns BookInfo for given BID")
	public BookInfo getBookById(@PathVariable Integer bookId) {
		log.info("---BookController---getBookById()-----");
		return bookService.getBookInfo(bookId);
	}

}

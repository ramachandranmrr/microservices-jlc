package com.jlcindia.booksearch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class BookSearchController {

	static Logger log = LoggerFactory.getLogger(BookSearchController.class);

	@Autowired
	BookPriceProxy bookPriceProxy;

	@GetMapping("/mybook/{bookId}")
	public BookInfo getBookById(@PathVariable Integer bookId) {
		log.info("---BookSearchController---getBookById()-----");

		BookInfo bookInfo = new BookInfo();

		bookInfo.setBookId(bookId);
		bookInfo.setBookName("Java books");
		bookInfo.setAuthor("Sriniva Dande");
		bookInfo.setCategory("Java");
		bookInfo.setPublications("JLC");

		BookPriceInfo bookPriceInfo = bookPriceProxy.getBookPrice(bookId);

		bookInfo.setPrice(bookPriceInfo.getPrice());
		bookInfo.setOffer(bookPriceInfo.getOffer());
		bookInfo.setServerPort(bookPriceInfo.getServerPort());

		return bookInfo;

	}

}

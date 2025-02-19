package com.jlcindia.bookstoreweb;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BookStoreController {
	
	static Logger log = LoggerFactory.getLogger(BookStoreController.class);

	@Autowired
	BookSearchProxy bookSearchProxy;
	
	@Value("${server.port}")
	String bookStoreServerPort;

	@GetMapping("/mybook/{bookId}")
	public BookInfo getBookById(@PathVariable Integer bookId) {
		log.info("---BookStoreController---getBookById()-----" + bookStoreServerPort);
		
		
		// Need to Invoke BookPriceMS
		// Start Here
		
		BookInfo bookInfo = bookSearchProxy.getBookById(bookId);
		bookInfo.setBookStoreServerPort(bookStoreServerPort);
		// End Here
		
		return bookInfo;
	}

}

package com.jlcindia.bookprice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin // CORS
@RestController
public class BookPriceController {
	
static Logger log = LoggerFactory.getLogger(BookPriceController.class);
	
	@Value("${server.port}")
	String serverPort;
	
	@GetMapping("/bookPrice/{bookId}")
	public BookPriceInfo getBookPrice(@PathVariable Integer bookId) {
		log.info("---BookPriceController---getBookPrice()----- " +serverPort);
		BookPriceInfo bookPriceInfo = new BookPriceInfo(bookId, 5000, 20, serverPort);
		
		return bookPriceInfo;
	}
	
}


package com.jlcindia.booksearch;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@CrossOrigin
@RestController
public class BookSearchController {
	
static Logger log = LoggerFactory.getLogger(BookSearchController.class);
	
	@GetMapping("/mybook/{bookId}")
	public BookInfo getBookById(@PathVariable Integer bookId) {
		log.info("---BookController---getBookById()-----");
		
		BookInfo bookInfo = new BookInfo();
		
		bookInfo.setBookId(bookId);
		bookInfo.setBookName("Java books");
		bookInfo.setAuthor("Sriniva Dande");
		bookInfo.setCategory("Java");
		bookInfo.setPublications("JLC");
		
		// start here
		RestTemplate restTemplate = new RestTemplate();
		String baseUrl = "http://localhost:9000";
		String apiUrl = "/bookPrice/"+bookId;
		String endPoint = baseUrl + apiUrl;
		
		ResponseEntity<BookPriceInfo> entityBookPriceInfo = 
					restTemplate.getForEntity(endPoint, BookPriceInfo.class);
		
		BookPriceInfo bookPriceInfo = entityBookPriceInfo.getBody();
		
		bookInfo.setPrice(bookPriceInfo.getPrice());
		bookInfo.setOffer(bookPriceInfo.getOffer());
		bookInfo.setServerPort(bookPriceInfo.getServerPort());
		
		// end here
		
		return bookInfo;
		
	}

}

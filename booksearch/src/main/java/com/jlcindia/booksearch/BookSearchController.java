package com.jlcindia.booksearch;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@CrossOrigin
@RestController
public class BookSearchController {

	static Logger log = LoggerFactory.getLogger(BookSearchController.class);
	
	@Autowired
	DiscoveryClient discoveryClient;
	
	@GetMapping("/mybook/{bookId}")
	public BookInfo getBookById(@PathVariable Integer bookId) {
		log.info("---BookController---getBookById()-----");
		
		BookInfo bookInfo = new BookInfo();
		
		bookInfo.setBookId(bookId);
		bookInfo.setBookName("Java books");
		bookInfo.setAuthor("Sriniva Dande");
		bookInfo.setCategory("Java");
		bookInfo.setPublications("JLC");
		
		//Need to Invoke BookPriceMS
		// Start Here
		List<ServiceInstance> instancesList = discoveryClient.getInstances("bookprice");
		for (ServiceInstance myInstance : instancesList) {
			System.out.println("Hello : " + myInstance.getUri());
		}
		String baseURL = instancesList.get(0).getUri().toString(); // Think On this

		System.out.println("Base URL : " + baseURL);
		RestTemplate restTemp = new RestTemplate();
		String apiURL = "/bookPrice/" + bookId;
		String endpoint = baseURL + apiURL;

		ResponseEntity<BookPriceInfo> respEntity = restTemp.getForEntity(endpoint, BookPriceInfo.class);
		BookPriceInfo bookPriceInfo = respEntity.getBody();
		
		return bookInfo;
		
	}

}

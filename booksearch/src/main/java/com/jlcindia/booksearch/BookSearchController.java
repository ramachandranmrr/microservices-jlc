package com.jlcindia.booksearch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RefreshScope
public class BookSearchController {
	
	static Logger log = LoggerFactory.getLogger(BookSearchController.class);
	
	@Value("${server.port}")
	String searchPort;
	
	@Value("${jlc.message:}")
	String searchMessage;
	
	@GetMapping("/jlcbooks")
	public BookInfo getBook() {
		log.info("---BookSearchController---getAllBooks()---");
		
		BookInfo bookInfo=new BookInfo();
		
		bookInfo.setBookId(101);
		bookInfo.setBookName("Master Spring MicroServices");
		bookInfo.setAuthor("Srinivas Dande");
		bookInfo.setSearchPort(searchPort);
		bookInfo.setSearchMessage(searchMessage);
		return bookInfo;
	}
	
}


package com.jlcindia.bookstoreweb;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class BookStoreController {
	
	static Logger log = LoggerFactory.getLogger(BookStoreController.class);

	@Autowired
	BookSearchProxy bookSearchProxy;
	
	@Value("${server.port}")
	String storePort;
	
	@Value("${jlc.message:}")
	String storeMessage;

	@GetMapping("/mybooks")
	public BookInfo getBook() {
		
		System.out.println("---BookStoreController---getBook()---");
		BookInfo bookInfo = bookSearchProxy.getBook();
		bookInfo.setStorePort(storePort);
		bookInfo.setStoreMessage(storeMessage);
		return bookInfo;
	}

}

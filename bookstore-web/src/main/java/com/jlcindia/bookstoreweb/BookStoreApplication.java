package com.jlcindia.bookstoreweb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// http://localhost:5000/showAllBooks
@SpringBootApplication
public class BookStoreApplication {

	static Logger log = LoggerFactory.getLogger(BookStoreApplication.class);

	public static void main(String[] args) {
		
		log.info(" BookStore - Begin ");
		SpringApplication.run(BookStoreApplication.class, args);
		
		log.info(" BookStore - started... ");
	}

}

package com.jlcindia.bookstoreweb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

// https://www.bezkoder.com/swagger-3-annotations/
// http://localhost:5000/showAllBooks
@SpringBootApplication
@EnableFeignClients 
public class BookStoreWebApplication {

	static Logger log = LoggerFactory.getLogger(BookStoreWebApplication.class);

	public static void main(String[] args) {
		log.info(" BookStoreWeb - Begin ");
		SpringApplication.run(BookStoreWebApplication.class, args);
		log.info(" BookStoreWeb - started... ");
	}

}

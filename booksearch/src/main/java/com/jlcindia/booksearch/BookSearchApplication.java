package com.jlcindia.booksearch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

// https://www.bezkoder.com/swagger-3-annotations/
// http://localhost:8000/swagger-ui/index.html
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.jlcindia.booksearch")
public class BookSearchApplication {

	static Logger log = LoggerFactory.getLogger(BookSearchApplication.class);

	public static void main(String[] args) {
		log.info(" BookSearchMS - Begin ");
		SpringApplication.run(BookSearchApplication.class, args);
		log.info(" BookSearchMS - started... ");
	}

}

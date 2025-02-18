package com.jlcindia.booksearch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

// https://www.bezkoder.com/swagger-3-annotations/
// http://localhost:8000/swagger-ui/index.html
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Book API", version = "2.0", description = "Book Information"))
@EnableFeignClients
@EnableDiscoveryClient
public class BookSearchApplication {

	static Logger log = LoggerFactory.getLogger(BookSearchApplication.class);

	public static void main(String[] args) {
		log.info(" BookSearchMS - Begin ");
		SpringApplication.run(BookSearchApplication.class, args);
		log.info(" BookSearchMS - started... ");
	}

}

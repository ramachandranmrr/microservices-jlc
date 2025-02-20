package com.jlcindia.bookprice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

// https://www.bezkoder.com/swagger-3-annotations/
// http://localhost:9000/swagger-ui/index.html
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Book API", version = "2.0", description = "Book Information"))
@EnableDiscoveryClient
@EnableCircuitBreaker
public class BookPriceApplication {

	static Logger log = LoggerFactory.getLogger(BookPriceApplication.class);

	public static void main(String[] args) {
		log.info(" BookPriceMS - Begin ");
		SpringApplication.run(BookPriceApplication.class, args);
		log.info("\n\n BookPriceMS - started... ");
	}

}

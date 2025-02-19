package com.jlcindia.bookprice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

// https://www.bezkoder.com/swagger-3-annotations/
// http://localhost:9000/swagger-ui/index.html
@SpringBootApplication
@EnableDiscoveryClient
public class BookpriceApplication {

	static Logger log = LoggerFactory.getLogger(BookpriceApplication.class);

	public static void main(String[] args) {
		log.info(" BookPriceMS - Begin ");
		SpringApplication.run(BookpriceApplication.class, args);
		log.info(" BookPriceMS - started... ");
	}

}

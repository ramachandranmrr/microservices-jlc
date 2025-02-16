package com.jlcindia.placeorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

// https://www.bezkoder.com/swagger-3-annotations/
// http://localhost:7000/swagger-ui/index.html
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Book API", version = "2.0", description = "Book Information"))
public class PlaceOrderApplication {

	static Logger log = LoggerFactory.getLogger(PlaceOrderApplication.class);

	public static void main(String[] args) {
		log.info(" PlaceOrderMS - Begin ");
		SpringApplication.run(PlaceOrderApplication.class, args);
		log.info(" PlaceOrderMS - started... ");
	}

}

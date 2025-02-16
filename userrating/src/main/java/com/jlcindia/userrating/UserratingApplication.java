package com.jlcindia.userrating;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

// https://www.bezkoder.com/swagger-3-annotations/
// http://localhost:9000/swagger-ui/index.html
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Book API", version = "2.0", description = "Book Information"))
public class UserratingApplication {

	static Logger log = LoggerFactory.getLogger(UserratingApplication.class);

	public static void main(String[] args) {
		log.info("UserratingMS - Begin ");
		SpringApplication.run(UserratingApplication.class, args);
		log.info("UserratingMS - Started... ");
	}

}

package com.jlcindia.apigatewayserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayServerApplication {

	static Logger log = LoggerFactory.getLogger(ApiGatewayServerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayServerApplication.class, args);
		
		log.info("\n\nApiGatewayServerApplication - started... ");
	}

}

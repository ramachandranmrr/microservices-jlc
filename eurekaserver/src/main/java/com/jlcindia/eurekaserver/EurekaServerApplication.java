package com.jlcindia.eurekaserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {

	static Logger log = LoggerFactory.getLogger(EurekaServerApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(EurekaServerApplication.class, args);

		log.info(" EurekaServerApplication - started... ");
	}

}

package com.jlcindia.userrating;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

// https://www.bezkoder.com/spring-boot-swagger-3/#google_vignette
@SpringBootApplication
public class UserratingConfig implements WebMvcConfigurer {


	// UserRating Message
	// A.UserRating Exchange
	@Bean(name = "myUserRatingsExchange")
	Exchange createUserRatingsExchange() {
		return ExchangeBuilder.topicExchange("myuser.ratings.exchange").build();
	}

	// B.UserRating Queue
	@Bean(name = "myUserRatingsQueue")
	Queue createUserRatingsQueue() {
		return QueueBuilder.durable("myuser.ratings.queue").build();
	}
	
	// C.UserRating Binding
	@Bean
	Binding userRatingBinding(Queue myUserRatingsQueue, TopicExchange myUserRatingsExchange) {
		return BindingBuilder
				.bind(myUserRatingsQueue)
				.to(myUserRatingsExchange)
				.with("myuser.ratings.key");
	}

	@Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("JLC BookStore API")
                        .description("User Rating API - part of BookStore")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Srinivas Dande")
                                .url("https://www.jlcindia.com")
                                .email("sri@jlcindia.com"))
                        .license(new License()
                                .name("API Under Free Licence")
                                .url("https://www.jlcindia.com")))
                .externalDocs(new ExternalDocumentation()
                        .description("JLC Documentation")
                        .url("https://www.jlcindia.com"));
    }
	
	/*@Bean
	public Docket api() {
	    return new Docket(DocumentationType.SWAGGER_2)
	        .select()
	        .apis(RequestHandlerSelectors.basePackage("com.jlcindia.bookprice"))
	        .paths(PathSelectors.any())
	        .build()
	        .apiInfo(getApiDetails());
	}*/

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html")
				.addResourceLocations("classpath:/METAINF/resources/");
		registry.addResourceHandler("/webjars/**")
				.addResourceLocations("classpath:/METAINF/resources/webjars/");
	}
	
	
}

/*
@Configuration
public class OpenAPIConfig {

  @Value("${bezkoder.openapi.dev-url}")
  private String devUrl;

  @Value("${bezkoder.openapi.prod-url}")
  private String prodUrl;

  @Bean
  public OpenAPI myOpenAPI() {
    Server devServer = new Server();
    devServer.setUrl(devUrl);
    devServer.setDescription("Server URL in Development environment");

    Server prodServer = new Server();
    prodServer.setUrl(prodUrl);
    prodServer.setDescription("Server URL in Production environment");

    Contact contact = new Contact();
    contact.setEmail("bezkoder@gmail.com");
    contact.setName("BezKoder");
    contact.setUrl("https://www.bezkoder.com");

    License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

    Info info = new Info()
        .title("Tutorial Management API")
        .version("1.0")
        .contact(contact)
        .description("This API exposes endpoints to manage tutorials.").termsOfService("https://www.bezkoder.com/terms")
        .license(mitLicense);

    return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
  }
} */

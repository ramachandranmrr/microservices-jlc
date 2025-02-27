package com.jlcindia.bookstoreweb;

import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@SpringBootApplication
@Configuration
public class BookStoreConfig implements WebMvcConfigurer {

	@Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("JLC BookStore API")
                        .description("BookSearch API - part of BookStore")
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

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/METAINF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/METAINF/resources/webjars/");
	}
	
	@Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOrigins(List.of("http://localhost:5173"));
        corsConfig.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        corsConfig.setAllowedHeaders(List.of("*"));
        corsConfig.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsFilter(source);
    }
	
}

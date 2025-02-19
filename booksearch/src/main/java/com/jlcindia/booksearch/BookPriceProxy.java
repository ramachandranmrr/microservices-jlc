package com.jlcindia.booksearch;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="zuulgateway")
public interface BookPriceProxy {
	
	@GetMapping("/bookprice/bookPrice/{bookId}")
	public BookPriceInfo getBookPrice(@PathVariable Integer bookId);

}

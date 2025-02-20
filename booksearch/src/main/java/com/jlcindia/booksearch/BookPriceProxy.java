package com.jlcindia.booksearch;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name="bookprice",url = "http://localhost:9000")
@FeignClient("zuulgateway")
public interface BookPriceProxy {
	
	@GetMapping("/book-price/bookPrice/{bookId}")
	public BookPriceInfo getBookPrice(@PathVariable Integer bookId);
	
	@GetMapping("/book-price/offeredPrice/{bookId}")
	public double getOfferedPrice(@PathVariable Integer bookId);
		
}

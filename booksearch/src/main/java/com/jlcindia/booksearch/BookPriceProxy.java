package com.jlcindia.booksearch;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="bookprice")
public interface BookPriceProxy {
	@GetMapping("/bookPrice/{bookId}")
	public BookPriceInfo getBookPrice(@PathVariable Integer bookId);
	
	@GetMapping("/offeredPrice/{bookId}")
	public double getOfferedPrice(@PathVariable Integer bookId);
}

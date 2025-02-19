package com.jlcindia.bookstoreweb;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="zuulgateway")
public interface BookSearchProxy {
	
	@GetMapping("/booksearch/mybook/{bookId}")
	public BookInfo getBookById(@PathVariable Integer bookId);
	
}


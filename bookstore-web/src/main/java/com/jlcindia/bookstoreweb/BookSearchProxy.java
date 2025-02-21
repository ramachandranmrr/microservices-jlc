package com.jlcindia.bookstoreweb;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="booksearch")
public interface BookSearchProxy {

	@GetMapping("/jlcbooks")
	public BookInfo getBook();
	
}

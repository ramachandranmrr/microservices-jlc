package com.jlcindia.bookstoreweb;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="booksearch")
public interface BookSearchProxy {

	@GetMapping("/jlcbooks")
	public List<String> getAllBooks();
	
}

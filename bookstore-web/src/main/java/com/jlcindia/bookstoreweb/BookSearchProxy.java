package com.jlcindia.bookstoreweb;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// @FeignClient(name="booksearch",url = "http://localhost:8000")
@FeignClient(name="zuulgateway")
public interface BookSearchProxy {

	@GetMapping("/book-price/bookPrice/{bookId}")
	public BookPriceInfo getBookPrice(@PathVariable Integer bookId);
	
	@GetMapping("/book-price/offeredPrice/{bookId}")
	public double getOfferedPrice(@PathVariable Integer bookId);
	
	@GetMapping("/book-price/mybooks/{author}/{category}")
	public List<Book> getBooks(@PathVariable String author, @PathVariable String category);
	
	@GetMapping("/book-price/mybook/{bookId}")
	public BookInfo getBookById(@PathVariable Integer bookId);

}

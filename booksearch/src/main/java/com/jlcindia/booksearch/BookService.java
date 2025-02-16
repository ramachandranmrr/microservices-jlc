package com.jlcindia.booksearch;

import java.util.List;

public interface BookService {
	public List<Book> getBooks(String author, String category);

	public BookInfo getBookInfo(Integer bookId);

}
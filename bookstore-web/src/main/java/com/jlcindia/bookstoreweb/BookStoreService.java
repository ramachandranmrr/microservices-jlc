package com.jlcindia.bookstoreweb;

import java.util.List;

public interface BookStoreService {
	
    List<Book> getBooks();
    
    Book getBookById(Integer bookId);
    
    Book addBook(Book book);
    
    Book updateBook(Integer bookId, Book book);
    
    void deleteById(Integer bookId);
    
}

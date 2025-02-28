package com.jlcindia.bookstoreweb;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookStoreServiceImpl implements BookStoreService {
	
	static Logger log = LoggerFactory.getLogger(BookStoreServiceImpl.class);
	
	@Autowired
	BookStoreDAO bookStoreDAO ;
	
	@Override
    public List<Book> getBooks() {
        return bookStoreDAO.findAll();
    }

    @Override
    public Book getBookById(Integer bookId) {
        return bookStoreDAO.findById(bookId).orElse(null);
    }

    @Override
    public Book addBook(Book book) {
        return bookStoreDAO.save(book);
    }

    @Override
    public Book updateBook(Integer bookId, Book book) {
        Optional<Book> existingBook = bookStoreDAO.findById(bookId);
        if (existingBook.isPresent()) {
            book.setBookId(bookId); // Ensure ID remains unchanged
            return bookStoreDAO.save(book);
        }
        return null;
    }

    @Override
    public void deleteById(Integer bookId) {
        bookStoreDAO.deleteById(bookId);
    }
	
}

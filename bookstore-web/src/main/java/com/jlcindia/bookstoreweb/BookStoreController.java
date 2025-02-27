package com.jlcindia.bookstoreweb;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// http://localhost:5000/swagger-ui/index.html
@RestController
@RequestMapping("/api/books") 
@CrossOrigin(origins = "http://localhost:5173")
public class BookStoreController {

    static Logger log = LoggerFactory.getLogger(BookStoreController.class);
    
    @Autowired
    BookStoreService bookStoreService;

    @GetMapping
    public Collection<Book> getBooks() {
        return bookStoreService.getAllBooks();
    }

    @GetMapping("/{bookId}")
    public Book getBookInfo(@PathVariable Integer bookId) {
        return bookStoreService.getBookById(bookId);
    }
    
    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookStoreService.addBook(book);
    }
    
    @PutMapping("/{bookId}")
    public Book updateBook(@PathVariable Integer bookId, @RequestBody Book updatedBook) {
        return bookStoreService.updateBook(bookId, updatedBook);
    }
    
    @DeleteMapping("/{bookId}")
    public ResponseEntity<String> deleteById(@PathVariable Integer bookId) {
    	bookStoreService.deleteById(bookId);
        return ResponseEntity.ok("Book deleted successfully");
    }

}

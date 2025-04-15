package com.jlcindia.booksearch;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BookServiceImpl implements BookService {
	static Logger log = LoggerFactory.getLogger(BookServiceImpl.class);
	@Autowired
	BookInventoryDAO bookInventoryDAO;
	@Autowired
	BookRatingDAO bookRatingDAO;
	@Autowired
	BookDAO bookDAO;
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public List<Book> getBooks(String author, String category) {
		List<Book> mybooks = new ArrayList<>();
		if (author.equals("All Authors") && category.equals("All Categories")) {
			mybooks = bookDAO.findAll();
		} else if (author.equals("All Authors") && !category.equals("All Categories")) {
			mybooks = bookDAO.getBooksByCategory(category);
		} else if (!author.equals("All Authors") && category.equals("All Categories")) {
			mybooks = bookDAO.getBooksByAuthor(author);
		} else {
			mybooks = bookDAO.getBooksByAuthorAndCategory(author, category);
		}
		return mybooks;
	}

	public BookInfo getBookInfo(Integer bookId) {
		BookInfo bookInfo = new BookInfo();
		
		// 1. Book Details
		Book book = bookDAO.findById(bookId).get();
		bookInfo.setBookId(book.getBookId()); // 1
		bookInfo.setBookName(book.getBookName());// 2
		bookInfo.setAuthor(book.getAuthor()); // 3
		bookInfo.setPublications(book.getPublications());// 4
		bookInfo.setCategory(book.getCategory());// 5
		
		// 2. Book Rating Details
		BookRating bookRating = bookRatingDAO.findById(bookId).get();
		bookInfo.setAvgRating(bookRating.getAvgRating());// 6
		bookInfo.setNumberOfSearches(bookRating.getNumberOfSearches());// 7
		
		// 3. Book Inventory Details
		BookInventory bookInventory = bookInventoryDAO.findById(bookId).get();
		bookInfo.setBooksAvailable(bookInventory.getBooksAvailable());// 8
		
		// 4.Book Price Details – Invoking BookPriceMS
		//RestTemplate bookPriceRest = new RestTemplate();
		// String endpoint = "http://localhost:9000/bookPrice/" + bookId;
		String endpoint = "http://bookprice/bookPrice/" + bookId; // uses service name from Eureka
		BookPriceInfo bpInfo = restTemplate.getForObject(endpoint, BookPriceInfo.class);
		
		bookInfo.setPrice(bpInfo.getPrice());// 9
		bookInfo.setOffer(bpInfo.getOffer());// 10
		
		return bookInfo;
	}

	@Override
	public void updateBookRating(BookRating bookRating) {
		bookRatingDAO.save(bookRating);
	}

	@Override
	public void updateBookInventory(BookInventory bookInventory) {
		bookInventoryDAO.save(bookInventory);
	}
	
}

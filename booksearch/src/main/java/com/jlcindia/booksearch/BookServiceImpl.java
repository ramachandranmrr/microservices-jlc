package com.jlcindia.booksearch;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	BookPriceProxy bookPriceProxy;
	
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
		// RestTemplate bookPriceRest = new RestTemplate();
		// String endpoint = "http://localhost:9000/bookPrice/" + bookId;
		// BookPriceInfo bpInfo = bookPriceRest.getForObject(endpoint, BookPriceInfo.class);
		
		//Invoking BookPrice-MS using Feign
		BookPriceInfo bpInfo= bookPriceProxy.getBookPrice(bookId);
		bookInfo.setPrice(bpInfo.getPrice());// 9
		bookInfo.setOffer(bpInfo.getOffer());// 10
		return bookInfo;
		
	}

	@RabbitListener(queues = "mybook.ratings.queue")
	public void updateBookRating(BookRating bookRatingInfo) {
		
		System.out.println("BookServiceImpl - updateBookRating");
		BookRating bookRating= new BookRating();
		bookRating.setBookId(bookRatingInfo.getBookId());
		bookRating.setAvgRating(bookRatingInfo.getAvgRating());
		bookRating.setNumberOfSearches(bookRatingInfo.getNumberOfSearches());
		
		bookRatingDAO.save(bookRating);
		
	}

	@RabbitListener(queues = "myinventory.queue")
	public void updateBookInventory(BookInventory bookInventoryInfo) {
		System.out.println("BookServiceImpl - updateBookInventory");
		BookInventory bookInventory= new BookInventory();
		bookInventory.setBookId(bookInventoryInfo.getBookId());
		bookInventory.setBooksAvailable(bookInventoryInfo.getBooksAvailable());
		
		bookInventoryDAO.save(bookInventory);
		
	}
	
}

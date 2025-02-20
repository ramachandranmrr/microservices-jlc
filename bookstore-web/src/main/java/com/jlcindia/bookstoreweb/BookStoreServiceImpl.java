package com.jlcindia.bookstoreweb;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jlcindia.rabbitmq.OrderFullInfo;
import com.jlcindia.rabbitmq.OrderInfo;
import com.jlcindia.rabbitmq.OrderItemInfo;
import com.jlcindia.rabbitmq.UserRatingInfo;

@Service
public class BookStoreServiceImpl implements BookStoreService {

	static Logger log = LoggerFactory.getLogger(BookStoreServiceImpl.class);
	
	Map<Integer, Book> booksMap = new LinkedHashMap<>();
	
	@Autowired
	RabbitTemplate rabbitTemplate;
	
	@Autowired
	BookSearchProxy bookSearchProxy;
	
	@Autowired
	PlaceOrderProxy placeOrderProxy;
	
	@Autowired
	UserRatingProxy userRatingProxy;

	public List<String> getAuthorsList() {
		List<String> authorsList = new ArrayList<>();
		authorsList.add("All Authors");
		authorsList.add("Srinivas");
		authorsList.add("Vas");
		authorsList.add("Sri");
		return authorsList;
	}

	public List<String> getCategoryList() {
		List<String> catList = new ArrayList<>();
		catList.add("All Categories");
		catList.add("Web");
		catList.add("Spring");
		return catList;
	}

	public List<Book> getMyBooks(String author, String category) {
		System.out.println("BookStoreServiceImpl - getBooks()");
		if (author == null || author.length() == 0) {
			author = "All Authors";
		}
		if (category == null || category.length() == 0) {
			category = "All Categories";
		}
		// Invoking BookSearch Rest API with Feign
		List<Book> bookList = bookSearchProxy.getBooks(author, category);
		return bookList;
	}

	public BookInfo getBookInfoByBookId(Integer bookId) {
		System.out.println("BookStoreServiceImpl - getBookInfoByBookId()");
		// Invoking BookSearch Rest API with Feign
		BookInfo bookInfo = bookSearchProxy.getBookById(bookId);
		return bookInfo;
	}

	public Book getBookByBookId(Integer bookId) {
		System.out.println("BookStoreServiceImpl - getBookByBookId()");
		System.out.println(bookId);
		Book mybook = booksMap.get(bookId);
		return mybook;
	}

	public void placeOrder(Map<Integer, Book> mycartMap) {
		// New Code
		System.out.println("---2.BookStoreServiceImpl--placeOrder()----");
		List<OrderItemInfo> itemList = new ArrayList<>();
		double totalPrice = 0.0;
		int totalQuantity = 0;
		for (Book mybook : mycartMap.values()) {
			Integer bookId = mybook.getBookId();
			// Invoke BookPriceMS Rest API with Feign
			double offerPrice = bookSearchProxy.getOfferedPrice(bookId);
			OrderItemInfo item = new OrderItemInfo(0, bookId, 1, offerPrice);
			itemList.add(item);
			totalPrice = totalPrice + offerPrice;
			totalQuantity = totalQuantity + 1;
		}
		Date today = Calendar.getInstance().getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm");
		String orderDate = formatter.format(today);
		System.out.println(orderDate);
		OrderInfo orderInfo = new OrderInfo(orderDate, "U-111", totalQuantity, totalPrice, "New");
		OrderFullInfo orderFullInfo = new OrderFullInfo();
		orderFullInfo.setOrder(orderInfo);
		orderFullInfo.setItemsList(itemList);
		// Sending Order Message to RabbitMQ
		rabbitTemplate.convertAndSend("myorder.exchange", "myorder.key", orderFullInfo);
		System.out.println("Order Placed");
	}

	@Override
	public List<Order> getMyOrders(String userId) {
		// Invoke PlaceOrderMS Rest API with Feign
		List<Order> myorders = placeOrderProxy.getOrdersByUserId(userId);
		return myorders;
	}

	public void addUserRating(UserRating userRating) {
		System.out.println("---2.BookStoreServiceImpl--addUserRating()----");
		// Sending UserRating Message RabbitMQ
		UserRatingInfo userRatingInfo = new UserRatingInfo(userRating.getUserId(), userRating.getBookId(),
				userRating.getRating(), userRating.getReview());
		rabbitTemplate.convertAndSend("myuser.ratings.exchange", "myuser.ratings.key", userRatingInfo);
		System.out.println("Rating Added");
	}

	public List<UserRating> getMyRatings(String userId) {
		// Invoke UserRatingMS Rest API with Feign
		List<UserRating> ratingsList = userRatingProxy.getUserRatingByUserId(userId);
		return ratingsList;
	}
}

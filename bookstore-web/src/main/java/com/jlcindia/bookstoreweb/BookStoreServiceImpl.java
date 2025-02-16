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
import org.springframework.web.client.RestTemplate;

import com.jlcindia.rabbitmq.OrderFullInfo;
import com.jlcindia.rabbitmq.OrderInfo;
import com.jlcindia.rabbitmq.OrderItemInfo;
import com.jlcindia.rabbitmq.UserRatingInfo;

@Service
public class BookStoreServiceImpl implements BookStoreService {
	static Logger log = LoggerFactory.getLogger(BookStoreServiceImpl.class);
	Map<Integer, Book> booksMap = new LinkedHashMap<>();
	
	@Autowired
	private RabbitTemplate rabbitTemplate;

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
		// Invoke BookSearchMS
		RestTemplate bookSearchRest = new RestTemplate();
		String endpoint = "http://localhost:8000/mybooks/" + author + "/" + category;
		List<Map> list = bookSearchRest.getForObject(endpoint, List.class);
		List<Book> bookList = new ArrayList<>();
		for (Map mymap : list) {
			Book mybook = convertMapToBook(mymap);
			bookList.add(mybook);
			booksMap.put(mybook.getBookId(), mybook);
		}
		return bookList;

	}
	
	public BookInfo getBookInfoByBookId(Integer bookId) {
		System.out.println("BookStoreServiceImpl - getBookInfoByBookId()");
		// Invoke BookSearchMS
		RestTemplate bookSearchRest = new RestTemplate();
		String endpoint = "http://localhost:8000/mybook/" + bookId;
		BookInfo bookInfo = bookSearchRest.getForObject(endpoint, BookInfo.class);
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
		
		// Invoke BookPrice MS
		RestTemplate bookPriceRest = new RestTemplate();
		String priceEndpoint = "http://localhost:9000/offeredPrice/" + bookId;
		double offerPrice = bookPriceRest.getForObject(priceEndpoint, Double.class);
		
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
		rabbitTemplate.convertAndSend("myorder.exchange","myorder.key",
		orderFullInfo);
		System.out.println("Order Placed");
	}
	
	public List<Order> getMyOrders(String userId) {
		// Invoke PlaceOrder MS
		String orderEndpoint = "http://localhost:7000/myorders/U-123";
		RestTemplate orderRest = new RestTemplate();
		List<Order> myorders = orderRest.getForObject(orderEndpoint, List.class);
		// You need to Implement Remaining
		return myorders;
	}
	
	public void addUserRating(UserRating userRating) {
		System.out.println("---2.BookStoreServiceImpl--addUserRating()----");
		//Sending UserRating Message RabbitMQ
		UserRatingInfo userRatingInfo=new UserRatingInfo(userRating.getUserId(),
		userRating.getBookId(), userRating.getRating(), userRating.getReview());
		rabbitTemplate.convertAndSend("myuser.ratings.exchange","myuser.ratings.key",
		userRatingInfo);
		System.out.println("Rating Added");
	}
	
	public List<UserRating> getMyRatings(String userId) {
		List<UserRating> ratingsList = new ArrayList<>();
		String ratingEndpoint = "http://localhost:6500/userRatings/" + userId;
		RestTemplate ratingRest = new RestTemplate();
		List<Map> mymap = ratingRest.getForObject(ratingEndpoint, List.class);
		for (Map map : mymap) {
			UserRating urtaing = convertMapToUserRating(map);
			ratingsList.add(urtaing);
			System.out.println(map);
		}
		return ratingsList;
	}
	
	private UserRating convertMapToUserRating(Map map) {
		UserRating rating = new UserRating();
		rating.setRatingId(new Integer(map.get("ratingId").toString()));
		rating.setUserId(map.get("userId").toString());
		rating.setBookId(new Integer(map.get("bookId").toString()));
		rating.setRating(new Double(map.get("rating").toString()));
		rating.setReview(map.get("review").toString());
		return rating;
	}
	
	private Book convertMapToBook(Map map) {
		Book mybook = new Book();
		mybook.setBookId(Integer.parseInt(map.get("bookId").toString()));
		mybook.setBookName((map.get("bookName").toString()));
		mybook.setAuthor((map.get("author").toString()));
		mybook.setPublications(map.get("publications").toString());
		mybook.setCategory(map.get("category").toString());
		return mybook;
	}
	
}

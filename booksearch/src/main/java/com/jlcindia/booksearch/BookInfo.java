package com.jlcindia.booksearch;

public class BookInfo {
	private Integer bookId;
	private String bookName;
	private String author;
	private String publications;
	private String category;
	private double price;
	private double offer;
	private String bookSearchServerPort;
	
	private String bookPriceServerPort;

	
	public BookInfo() {
		super();
	}

	public BookInfo(String bookName, String author, String publications, String category, double price, double offer,
			String bookSearchServerPort, String bookPriceServerPort) {
		super();
		this.bookName = bookName;
		this.author = author;
		this.publications = publications;
		this.category = category;
		this.price = price;
		this.offer = offer;
		this.bookSearchServerPort = bookSearchServerPort;
		this.bookPriceServerPort = bookPriceServerPort;
	}

	public BookInfo(Integer bookId, String bookName, String author, String publications, String category, double price,
			double offer, String bookSearchServerPort, String bookPriceServerPort) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.author = author;
		this.publications = publications;
		this.category = category;
		this.price = price;
		this.offer = offer;
		this.bookSearchServerPort = bookSearchServerPort;
		this.bookPriceServerPort = bookPriceServerPort;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublications() {
		return publications;
	}

	public void setPublications(String publications) {
		this.publications = publications;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getOffer() {
		return offer;
	}

	public void setOffer(double offer) {
		this.offer = offer;
	}

	public String getBookSearchServerPort() {
		return bookSearchServerPort;
	}

	public void setBookSearchServerPort(String bookSearchServerPort) {
		this.bookSearchServerPort = bookSearchServerPort;
	}

	public String getBookPriceServerPort() {
		return bookPriceServerPort;
	}

	public void setBookPriceServerPort(String bookPriceServerPort) {
		this.bookPriceServerPort = bookPriceServerPort;
	}

	
}

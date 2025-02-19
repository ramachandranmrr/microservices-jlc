package com.jlcindia.booksearch;

public class BookPriceInfo {
	private Integer bookId;
	private double price;
	private double offer;
	private String bookPriceServerPort;
	
	public BookPriceInfo() {
		super();
	}

	public BookPriceInfo(double price, double offer) {
		super();
		this.price = price;
		this.offer = offer;
	}
	
	public BookPriceInfo(Integer bookId, double price, double offer) {
		super();
		this.bookId = bookId;
		this.price = price;
		this.offer = offer;
	}

	public BookPriceInfo(Integer bookId, double price, double offer, String bookPriceServerPort) {
		super();
		this.bookId = bookId;
		this.price = price;
		this.offer = offer;
		this.bookPriceServerPort = bookPriceServerPort;
	}
	

	public String getBookPriceServerPort() {
		return bookPriceServerPort;
	}

	public void setBookPriceServerPort(String bookPriceServerPort) {
		this.bookPriceServerPort = bookPriceServerPort;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
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

}

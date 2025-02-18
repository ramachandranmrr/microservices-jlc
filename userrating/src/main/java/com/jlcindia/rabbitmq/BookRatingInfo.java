package com.jlcindia.rabbitmq;

import java.io.Serializable;

public class BookRatingInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer bookId;
	private double avgRating;
	private int numberOfSearches;
	
	public BookRatingInfo() {
		super();
	}
	
	public BookRatingInfo(double avgRating, int numberOfSearches) {
		super();
		this.avgRating = avgRating;
		this.numberOfSearches = numberOfSearches;
	}
	
	public BookRatingInfo(Integer bookId, double avgRating, int numberOfSearches) {
		super();
		this.bookId = bookId;
		this.avgRating = avgRating;
		this.numberOfSearches = numberOfSearches;
	}
	
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public double getAvgRating() {
		return avgRating;
	}
	public void setAvgRating(double avgRating) {
		this.avgRating = avgRating;
	}
	public int getNumberOfSearches() {
		return numberOfSearches;
	}
	public void setNumberOfSearches(int numberOfSearches) {
		this.numberOfSearches = numberOfSearches;
	}
	
}

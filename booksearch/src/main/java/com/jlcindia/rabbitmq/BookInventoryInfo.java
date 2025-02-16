package com.jlcindia.rabbitmq;

import java.io.Serializable;

public class BookInventoryInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer bookId;
	private int booksAvailable;
	
	public BookInventoryInfo() {
		super();
	}
	
	public BookInventoryInfo(int booksAvailable) {
		super();
		this.booksAvailable = booksAvailable;
	}
	
	public BookInventoryInfo(Integer bookId, int booksAvailable) {
		super();
		this.bookId = bookId;
		this.booksAvailable = booksAvailable;
	}
	
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public int getBooksAvailable() {
		return booksAvailable;
	}
	public void setBooksAvailable(int booksAvailable) {
		this.booksAvailable = booksAvailable;
	}
	
}

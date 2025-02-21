package com.jlcindia.booksearch;

public class BookInfo {
	private Integer bookId;
	private String bookName;
	private String author;
	private String searchPort;
	private String searchMessage;
	
	
	public BookInfo() {
		super();
	}
	
	public BookInfo(Integer bookId, String bookName, String author, String searchPort, String searchMessage) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.author = author;
		this.searchPort = searchPort;
		this.searchMessage = searchMessage;
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
	public String getSearchPort() {
		return searchPort;
	}
	public void setSearchPort(String searchPort) {
		this.searchPort = searchPort;
	}
	public String getSearchMessage() {
		return searchMessage;
	}
	public void setSearchMessage(String searchMessage) {
		this.searchMessage = searchMessage;
	}
	
}
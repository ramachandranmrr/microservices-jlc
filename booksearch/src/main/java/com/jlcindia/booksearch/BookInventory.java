package com.jlcindia.booksearch;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "mybookinventory", schema = "jlcbooksdb")
public class BookInventory {
	@Id
	@Column(name = "book_id")
	private Integer bookId;
	@Column(name = "books_available")
	private int booksAvailable;

	public BookInventory() {
		super();
	}

	public BookInventory(int booksAvailable) {
		super();
		this.booksAvailable = booksAvailable;
	}

	public BookInventory(Integer bookId, int booksAvailable) {
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

//Constrcutors
//Setetrs and Getters
}
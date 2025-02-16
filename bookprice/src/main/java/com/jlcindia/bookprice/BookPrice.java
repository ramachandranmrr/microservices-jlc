package com.jlcindia.bookprice;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "mybookprice", schema = "jlcbookpricedb")
@Schema(description = "Book Model Information")
public class BookPrice {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookId_generator")
	@SequenceGenerator(name = "bookId_generator", sequenceName = "mybookId_gen", initialValue = 110, allocationSize = 1)
	@Column(name = "book_id")
	
	@Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Book Id", example = "123")
	private Integer bookId;
	@Column(name = "price")
	
	@Schema(description = "Book price", example = "Swagger Tutorial")
	private double price;
	@Column(name = "offer")
	
	@Schema(description = "Book offer", example = "Swagger Tutorial")
	private double offer;
	
	public BookPrice() {
		
	}
	
	public BookPrice(double price, double offer) {
		super();
		this.price = price;
		this.offer = offer;
	}
	
	public BookPrice(Integer bookId, double price, double offer) {
		super();
		this.bookId = bookId;
		this.price = price;
		this.offer = offer;
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

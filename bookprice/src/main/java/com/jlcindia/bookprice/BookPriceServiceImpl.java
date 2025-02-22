package com.jlcindia.bookprice;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@RefreshScope
@Transactional
public class BookPriceServiceImpl implements BookPriceService {

	static Logger log = LoggerFactory.getLogger(BookPriceServiceImpl.class);
	
	@Autowired
	BookPriceDAO bookPriceDAO;
	
	@Value("${jlc.book.offer}")
	String myoffer;

		@Override
		public BookPrice getBookPriceById(Integer bookId) {
			log.info("---BookPriceServiceImpl---getBookPriceById()-----");
			Optional<BookPrice> bookPriceOpts = bookPriceDAO.findById(bookId);
			BookPrice bookPrice = null;
			if (bookPriceOpts.isPresent()) {
				bookPrice = bookPriceOpts.get();
			}
			int myspecialOffer = Integer.parseInt(myoffer);
			System.out.println("myspecialOffer : -- " + myspecialOffer);
			System.out.println("bookPrice.getOffer() : -- " + bookPrice.getOffer());
			if (myspecialOffer > bookPrice.getOffer()) {
				bookPrice.setOffer(myspecialOffer);
			}
			System.out.println(bookPrice);
			return bookPrice;
		}
	
		@Override
		public double getOfferedPriceById(Integer bookId) {
			log.info("---BookPriceServiceImpl---getOfferedPriceById()-----");
			int myspecialOffer = Integer.parseInt(myoffer);
			System.out.println("myspecialOffer : -- " + myspecialOffer);
			Optional<BookPrice> bookPriceOpts = bookPriceDAO.findById(bookId);
			double offeredPrice = 0.0;
			if (bookPriceOpts.isPresent()) {
				BookPrice bookPrice = bookPriceOpts.get();
				double actualPrice = bookPrice.getPrice();
				double offer = bookPrice.getOffer();
				double discountAmount = 0.0;
				if (myspecialOffer > offer) {
					discountAmount = actualPrice * myspecialOffer / 100;
				} else {
					discountAmount = actualPrice * offer / 100;
				}
				offeredPrice = actualPrice - discountAmount;
			}
			return offeredPrice;
		}
	
}

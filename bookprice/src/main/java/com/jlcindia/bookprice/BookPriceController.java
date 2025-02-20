package com.jlcindia.bookprice;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Book", description = "Book management APIs")
@CrossOrigin // CORS
@RestController
public class BookPriceController {

	// Create the logger Instance
	static Logger logger = LoggerFactory.getLogger(BookPriceController.class);

	@Autowired
	BookPriceService bookPriceService;

	@GetMapping("/bookPrice/{bookId}")
	@HystrixCommand(fallbackMethod = "getBookPriceFallback")
	public BookPrice getBookPrice(@PathVariable Integer bookId) {
		logger.info("---BookPriceController---getBookPrice()-----");
		if (1 == 1) {
			try {
				throw new ArithmeticException();
			} catch (ArithmeticException ex) {
				ex.printStackTrace();
				logger.error(ex.toString());
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				ex.printStackTrace(pw);
				String stackTrace = sw.toString();
				logger.error("MyException - " + stackTrace);
			}
		}
		return null;
	}

	@GetMapping("/offeredPrice/{bookId}")
	@HystrixCommand(fallbackMethod = "getOfferedPriceFallback")
	public double getOfferedPrice(@PathVariable Integer bookId) {
		logger.info("---BookPriceController---getOfferedPrice()-----");
		
		if (1 == 1) {
			try {
				throw new ArithmeticException();
			} catch (ArithmeticException ex) {
				ex.printStackTrace();
				logger.error(ex.toString());

				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				ex.printStackTrace(pw);
				String stackTrace = sw.toString();
				logger.error("MyException - " + stackTrace);
			}
		}
		return 0.0;
	}

	public BookPrice getBookPriceFallback(Integer bookId) {
		logger.info("---BookPriceController---getBookPriceFallback()-----");
		BookPrice bookPrice = bookPriceService.getBookPriceById(bookId);
		return bookPrice;
	}

	public double getOfferedPriceFallback(Integer bookId) {
		logger.info("---BookPriceController---getOfferedPriceFallback()-----");
		double offeredPrice = bookPriceService.getOfferedPriceById(bookId);
		return offeredPrice;
	}
}

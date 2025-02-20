package com.jlcindia.userrating;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin // CORS
@RestController
public class RatingController {

	//Create the logger Instance
	static Logger logger = LoggerFactory.getLogger(RatingController.class);
	
	@Autowired
	RatingService ratingService;

	@PutMapping("/addUserRating")
	public void addUserRating(@RequestBody UserRating userRating) {
		logger.info("---RatingController---addUserRating()-----");
		ratingService.addUserRating(userRating);
	}

	@GetMapping("/userRatings/{userId}")
	public List<UserRating> getUserRatingByUserId(@PathVariable String userId) {
		logger.info("---RatingController---getUserRatingByUserId()-----");
		return ratingService.getUserRatingByUserId(userId);
	}

	@GetMapping("/bookRatings/{bookId}")
	public BookRating getBookRatingByBookId(@PathVariable String bookId) {
		logger.info("---RatingController---getBookRatingByBookId()-----");
		return ratingService.getBookRatingByBookId(Integer.parseInt(bookId));
	}
	
}

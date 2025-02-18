package com.jlcindia.userrating;

import java.util.List;

public interface RatingService {
	public List<UserRating> getUserRatingByBookId(Integer bookId);

	public List<UserRating> getUserRatingByUserId(String userId);

	public BookRating getBookRatingByBookId(Integer bookId);
}
package com.jlcindia.userrating;

import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jlcindia.rabbitmq.BookRatingInfo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RatingServiceImpl implements RatingService {
	
	@Autowired
	BookRatingDAO bookRatingDAO;
	
	@Autowired
	UserRatingDAO userRatingDAO;

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public void addUserRating(UserRating userRating) {
		//1.Add the User Rating
		userRatingDAO.save(userRating);
		
		//2.Calculate the Avg rating for BookId
		int bookId = userRating.getBookId();// 101
		List<UserRating> ratingList = this.getUserRatingByBookId(bookId);
		double sumRating = 0.0;
		for (UserRating ur : ratingList) {
			sumRating = sumRating + ur.getRating();
		}
		double avgRating = sumRating / ratingList.size();
		
		//3.Update BookRating in UserRatingMS (Local)
		BookRating bookRating = this.getBookRatingByBookId(bookId);
		bookRating.setAvgRating(avgRating);
		bookRatingDAO.save(bookRating);
		
		//4.Update BookRating in BookSearchMS(Remote)
		BookRatingInfo bookRatingInfo=new BookRatingInfo();
		bookRatingInfo.setBookId(bookRating.getBookId());
		bookRatingInfo.setAvgRating(bookRating.getAvgRating());
		bookRatingInfo.setNumberOfSearches(bookRating.getNumberOfSearches());
		rabbitTemplate.convertAndSend("mybook.search.exchange","mybook.ratings.key", bookRatingInfo);
	
	}

	@Override
	public List<UserRating> getUserRatingByBookId(Integer bookId) {
		return userRatingDAO.findUserRatingByBookId(bookId);
	}

	@Override
	public List<UserRating> getUserRatingByUserId(String userId) {
		return userRatingDAO.findUserRatingByUserId(userId);
	}

	@Override
	public BookRating getBookRatingByBookId(Integer bookId) {
		BookRating bookRating = bookRatingDAO.findBookRatingByBookId(bookId);
		return bookRating;
	}
	
}

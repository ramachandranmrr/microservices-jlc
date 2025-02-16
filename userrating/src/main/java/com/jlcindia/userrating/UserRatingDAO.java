package com.jlcindia.userrating;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRatingDAO extends JpaRepository<UserRating, Integer> {
	public List<UserRating> findUserRatingByBookId(Integer bookId);

	public List<UserRating> findUserRatingByUserId(String userId);
}
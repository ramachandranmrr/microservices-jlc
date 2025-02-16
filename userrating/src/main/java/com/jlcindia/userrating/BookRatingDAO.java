package com.jlcindia.userrating;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRatingDAO extends JpaRepository<BookRating, Integer> {
public BookRating findBookRatingByBookId(Integer bookId);
}
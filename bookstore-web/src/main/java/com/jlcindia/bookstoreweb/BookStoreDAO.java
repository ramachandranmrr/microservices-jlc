package com.jlcindia.bookstoreweb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookStoreDAO extends JpaRepository<Book, Integer> {
	
}
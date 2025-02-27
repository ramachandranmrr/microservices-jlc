package com.jlcindia.bookstoreweb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookStroreDAO extends JpaRepository<Book, Integer> {
	
}
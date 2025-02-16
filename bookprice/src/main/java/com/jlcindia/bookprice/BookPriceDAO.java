package com.jlcindia.bookprice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookPriceDAO extends JpaRepository<BookPrice,Integer> {
}
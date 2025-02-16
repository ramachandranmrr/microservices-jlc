package com.jlcindia.placeorder;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemDAO extends JpaRepository<OrderItem, Integer> {
	List<OrderItem> findOrderItemsByOrderId(Integer orderId);
}
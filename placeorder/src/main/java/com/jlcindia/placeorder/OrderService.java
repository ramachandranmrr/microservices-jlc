package com.jlcindia.placeorder;

import java.util.List;

public interface OrderService {

	public List<Order> getOrdersByUserId(String userId);
}
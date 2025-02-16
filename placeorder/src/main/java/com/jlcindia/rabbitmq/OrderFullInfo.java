package com.jlcindia.rabbitmq;

import java.io.Serializable;
import java.util.List;

import com.jlcindia.placeorder.Order;
import com.jlcindia.placeorder.OrderItem;

public class OrderFullInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Order order;
	private List<OrderItem> itemsList;
	
	
	public OrderFullInfo() {
		super();
	}
	
	public OrderFullInfo(Order order, List<OrderItem> itemsList) {
		super();
		this.order = order;
		this.itemsList = itemsList;
	}
	
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public List<OrderItem> getItemsList() {
		return itemsList;
	}
	public void setItemsList(List<OrderItem> itemsList) {
		this.itemsList = itemsList;
	}
	
	
}

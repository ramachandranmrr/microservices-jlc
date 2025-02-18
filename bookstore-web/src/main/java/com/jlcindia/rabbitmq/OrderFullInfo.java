package com.jlcindia.rabbitmq;

import java.io.Serializable;
import java.util.List;

public class OrderFullInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private OrderInfo order;
	private List<OrderItemInfo> itemsList;
	
	public OrderFullInfo() {
		super();
	}
	
	public OrderFullInfo(OrderInfo order, List<OrderItemInfo> itemsList) {
		super();
		this.order = order;
		this.itemsList = itemsList;
	}
	
	public OrderInfo getOrder() {
		return order;
	}
	public void setOrder(OrderInfo order) {
		this.order = order;
	}
	public List<OrderItemInfo> getItemsList() {
		return itemsList;
	}
	public void setItemsList(List<OrderItemInfo> itemsList) {
		this.itemsList = itemsList;
	}

}

package com.jlcindia.placeorder;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
	
	static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	@Autowired
	OrderDAO orderDAO;
	@Autowired
	OrderItemDAO orderItemDAO;
	@Autowired
	BookInventoryDAO bookInventoryDAO;

	@Override
	public void placeOrder(OrderInfo orderInfo) {
		log.info("---OrderServiceImpl---placeOrder()-----");
		
		//1. Save the Order
		Order myorder = orderInfo.getOrder();
		myorder = orderDAO.save(myorder);// 5002
		int orderId = myorder.getOrderId();
		
		//2.Save OrderItems
		for (OrderItem myitem : orderInfo.getItemsList()) {
			myitem.setOrderId(orderId);
			orderItemDAO.save(myitem);
		}
		
		//3.Update Inventory at Two Places
		RestTemplate bookSearchRest = new RestTemplate();
		String endpoint = "http://localhost:8000/updateBookInventory";
		for (OrderItem myitem : orderInfo.getItemsList()) {
			Integer bookId = myitem.getBookId();
			BookInventory bookInventory = bookInventoryDAO.findById(bookId).get();
			bookInventory.setBooksAvailable(bookInventory.getBooksAvailable() - 1);
			
			//3A. Update Inventory of PlaceOrderMS Locally
			bookInventoryDAO.save(bookInventory);
			
			//3B. Update Inventory of BookSearchMS Remotely
			bookSearchRest.put(endpoint, bookInventory);
		}
	}

	@Override
	public List<Order> getOrdersByUserId(String userId) {
		log.info("---OrderServiceImpl---getOrdersByUserId()-----");
		List<Order> orderList = orderDAO.findOrdersByUserId(userId);
		return orderList;
	}
	
}

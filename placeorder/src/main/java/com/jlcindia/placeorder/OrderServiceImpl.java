package com.jlcindia.placeorder;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jlcindia.rabbitmq.BookInventoryInfo;

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
	
	@Autowired
	private RabbitTemplate rabbitTemplate;

	@RabbitListener(queues = "myorder.queue")
	public void placeOrder(OrderInfo orderInfo) {
		log.info("--3.OrderServiceImpl---placeOrder()-----");
		
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
		// RestTemplate bookSearchRest = new RestTemplate();
		// String endpoint = "http://localhost:8000/updateBookInventory";
		for (OrderItem orderItemInfo : orderInfo.getItemsList()) {
			Integer bookId=orderItemInfo.getBookId();
			BookInventory mybookInventory = bookInventoryDAO.findById(bookId).get();
			Integer currentStock=mybookInventory.getBooksAvailable();
			currentStock = currentStock - orderItemInfo.getQty();
			mybookInventory.setBooksAvailable(currentStock);
			
			// Update Local Inventory
			bookInventoryDAO.save(mybookInventory);
			
			//Update Inventory of BookSearchMS by Sending Message
			BookInventoryInfo bookInventoryInfo=new BookInventoryInfo();
			bookInventoryInfo.setBookId(mybookInventory.getBookId());
			bookInventoryInfo.setBooksAvailable(mybookInventory.getBooksAvailable());
			
			rabbitTemplate.convertAndSend("mybook.search.exchange",
						"myinventory.key", bookInventoryInfo);
			
		}
	}

	@Override
	public List<Order> getOrdersByUserId(String userId) {
		log.info("---OrderServiceImpl---getOrdersByUserId()-----");
		List<Order> orderList = orderDAO.findOrdersByUserId(userId);
		return orderList;
	}
	
}


package com.jlcindia.placeorder;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin // CORS
@RestController
public class PlaceOrderController {
	static Logger log = LoggerFactory.getLogger(PlaceOrderController.class);
	
	@Autowired
	OrderService orderService;

	@PostMapping("/placeOrder")
//@ApiOperation(value = " placeOrder", response = void.class, notes = "placeOrder")
	public void placeOrder(@RequestBody OrderInfo orderInfo) {
		log.info("---OrderController---placeOrder()-----");
		orderService.placeOrder(orderInfo);
	}

	@GetMapping("/myorders/{userId}")
// @ApiOperation(value = " getOrdersByUserId", response = List.class, notes = "Return Orders belongs User")
	public List<Order> getOrdersByUserId(@PathVariable String userId) {
		log.info("---OrderController---getOrdersByUserId()-----");
		List<Order> myoders = orderService.getOrdersByUserId(userId);
		return myoders;
	}
}
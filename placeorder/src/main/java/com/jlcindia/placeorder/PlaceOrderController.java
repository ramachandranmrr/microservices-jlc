package com.jlcindia.placeorder;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin // CORS
@RestController
public class PlaceOrderController {

	static Logger log = LoggerFactory.getLogger(PlaceOrderController.class);
	
	@Autowired
	OrderService orderService;

	@GetMapping("/myorders/{userId}")
// @ApiOperation(value = " getOrdersByUserId", response = List.class, notes = "Return Orders belongs User")
	public List<Order> getOrdersByUserId(@PathVariable String userId) {
		log.info("---OrderController---getOrdersByUserId()-----");
		List<Order> myoders = orderService.getOrdersByUserId(userId);
		return myoders;
	}
	
}
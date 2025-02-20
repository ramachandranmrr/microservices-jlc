package com.jlcindia.bookstoreweb;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// @FeignClient(name="placeorder",url = "http://localhost:7000")@FeignClient(name="placeorder",url = "http://localhost:7000")
@FeignClient(name="placeorder")
public interface PlaceOrderProxy {
	
	@GetMapping("/myorders/{userId}")
	public List<Order> getOrdersByUserId(@PathVariable String userId);
}

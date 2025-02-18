package com.jlcindia.bookstoreweb;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="userrating")
public interface UserRatingProxy {
	
	@GetMapping("/userRatings/{userId}")
	public List<UserRating> getUserRatingByUserId(@PathVariable String userId);
	
}

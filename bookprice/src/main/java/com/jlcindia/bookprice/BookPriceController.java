package com.jlcindia.bookprice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Book", description = "Book management APIs")
@CrossOrigin // CORS
@RestController
public class BookPriceController {
	
	static Logger log = LoggerFactory.getLogger(BookPriceController.class);
	
	@Autowired
	BookPriceService bookPriceService;

	@Operation(
		      summary = "Retrieve a book by Id",
		      description = "Get a book object by specifying its id. The response is book object with id, title, description and published status.")
	@GetMapping("/bookPrice/{bookId}")
	public BookPrice getBookPrice(@PathVariable Integer bookId) {
		log.info("---BookPriceController---getBookPrice()-----");
		BookPrice bookPrice = bookPriceService.getBookPriceById(bookId);
		return bookPrice;
	}

	@Operation(
		      summary = "Retrieve a book by Id",
		      description = "Get a book object by specifying its id. The response is book object with id, title, description and published status.")
	@GetMapping("/offeredPrice/{bookId}")
	public double getOfferedPrice(@PathVariable Integer bookId) {
		log.info("---BookPriceController---getOfferedPrice()-----");
		double offeredPrice = bookPriceService.getOfferedPriceById(bookId);
		return offeredPrice;
	}
	
	
	 /*@ApiResponses({
		    @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Tutorial.class), mediaType = "application/json") }),
		    @ApiResponse(responseCode = "404", description = "The Tutorial with given Id was not found.", content = { @Content(schema = @Schema()) })
		  }) 
	
	@GetMapping("/tutorials")
	  public ResponseEntity<Map<String, Object>> getAllTutorials(
	      @Parameter(description = "Search Tutorials by title") @RequestParam(required = false) String title,
	      @Parameter(description = "Page number, starting from 0", required = true) @RequestParam(defaultValue = "0") int page,
	      @Parameter(description = "Number of items per page", required = true) @RequestParam(defaultValue = "3") int size) {
	    
	  }*/
	
}


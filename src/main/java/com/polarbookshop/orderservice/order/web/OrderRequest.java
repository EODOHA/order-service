package com.polarbookshop.orderservice.order.web;

import jakarta.validation.constraints.*;

public record OrderRequest(
		
	@NotBlank(message = "The Book ISBN mus be defined.")
	String isbn,
	
	@NotNull(message = "The book qunatity must be defined.")
	@Min(value = 1, message = "You must order at least 1 item.")
	@Max(value = 5, message = "You cannot order more than 5 items.")
	Integer quantity
){
}

package com.polarbookshop.orderservice.order.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.polarbookshop.orderservice.order.domain.Order;
import com.polarbookshop.orderservice.order.domain.OrderService;

import jakarta.validation.Valid;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController	// 클래스가 스프링 컴포넌트임을 표시하는 스테레오타입 애너테이션.
				// REST 엔드포인트에 대한 핸들러가 정의되는 클래스임을 나타냄.
@RequestMapping("orders") // 클래스가 핸들러를 제공하는 URI의 루트 패스(/orders)를 식별함.
public class OrderController {
	private final OrderService orderService;
	
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}
	
	@GetMapping
	public Flux<Order> getAllOrders() {
		return orderService.getAllOrders();
	}
	
	@PostMapping
	public Mono<Order> submitOrder(@RequestBody @Valid OrderRequest orderRequest) {
		return orderService.submitOrder(
			orderRequest.isbn(), orderRequest.quantity()
		);
	}
}

package com.polarbookshop.orderservice.order.domain;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service // 이 클래스가 스프링에 의해 관리되는 서비스임을 표시하는 스테레오타입 애너테이션.
public class OrderService {
	private final OrderRepository orderRepository;
	
	public OrderService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}
	
	public Flux<Order> getAllOrders() { // 플럭스는 여러 주문을 위해 사용(0..N)
		return orderRepository.findAll();
	}
	
	public Mono<Order> submitOrder(String isbn, int quantity) {
		return Mono.just(buildRejectedOrder(isbn, quantity))
				// 주문 객체를 가지고 모노를 생성함.
			.flatMap(orderRepository::save);
				// 리액티브 스트림의 앞 단계에서 비동기적으로 생성된 주문 객체를 DB에 저장함.
	}
	
	public static Order buildRejectedOrder(
		String bookIsbn, int quantity
	) {	// 주문이 거부되면 ISBN, 수량, 상태만 지정함.
		// 스프링 데이터가 식별자, 버전, 감사 메타데이터를 알아서 처리해준다.
		return Order.of(bookIsbn, null, null, quantity, OrderStatus.REJECTED);
	}
}

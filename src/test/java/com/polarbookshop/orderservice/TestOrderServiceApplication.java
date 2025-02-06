package com.polarbookshop.orderservice;

import org.springframework.boot.SpringApplication;

import com.polarbookshop.orderservice.OrderServiceApplication;

public class TestOrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(OrderServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}

package com.dawn.microservice.product_service;

import org.springframework.boot.SpringApplication;

import com.dawn.microservice.productservice.ProductServiceApplication;

public class TestProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(ProductServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}

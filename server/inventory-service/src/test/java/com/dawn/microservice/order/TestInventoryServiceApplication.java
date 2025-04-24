package com.dawn.microservice.order;

import org.springframework.boot.SpringApplication;

import com.dawn.microservice.inventory.InventoryServiceApplication;

public class TestInventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(InventoryServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}

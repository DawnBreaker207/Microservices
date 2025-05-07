package com.dawn.microservice.order.service;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.dawn.microservice.order.client.InventoryClient;
import com.dawn.microservice.order.dto.OrderRequest;
import com.dawn.microservice.order.event.OrderPlacedEvent;
import com.dawn.microservice.order.model.Order;
import com.dawn.microservice.order.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    public void placeOrder(OrderRequest orderRequest) {

	var isProductInStock = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());

	if (isProductInStock) {

//	Map OrderRequest to order object
	    Order order = new Order();
	    order.setOrderNumber(UUID.randomUUID().toString());
	    order.setPrice(orderRequest.price().multiply(BigDecimal.valueOf(orderRequest.quantity())));
	    order.setSkuCode(orderRequest.skuCode());
	    order.setQuantity(orderRequest.quantity());
//	Save order to OrderRepository
	    orderRepository.save(order);

//	    Send the message to Kafka Topic
	    OrderPlacedEvent orderPlacedEvent = new OrderPlacedEvent();
	    orderPlacedEvent.setOrderNumber(order.getOrderNumber());
	    orderPlacedEvent.setEmail(orderRequest.userDetails().email());
	    orderPlacedEvent.setFirstName(orderRequest.userDetails().firstName());
	    orderPlacedEvent.setLastName(orderRequest.userDetails().lastName());
	    log.info("Start - Sending OrderPlacedEvent {} to Kafka topec order-placed", orderPlacedEvent);
	    kafkaTemplate.send("order-placed", orderPlacedEvent);
	    log.info("End - Sending OrderPlacedEvent {} to Kafka topec order-placed", orderPlacedEvent);
	} else {
	    throw new RuntimeException("Product with SkuCode " + orderRequest.skuCode() + " is not in stock");
	}
    }
}

package com.dawn.microservice.order.dto;

import java.math.BigDecimal;

public record OrderResponse(long id, String orderNumber, String skuCode, BigDecimal price, Integer quantity) {

}

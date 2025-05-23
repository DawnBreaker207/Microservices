package com.dawn.microservice.order.dto;

import java.math.BigDecimal;

public record OrderRequest(long id, String orderNumber, String skuCode, BigDecimal price, Integer quantity,
	UserDetails userDetails) {
    public record UserDetails(String email, String firstName, String lastName) {

    }
}

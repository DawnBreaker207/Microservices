package com.dawn.microservice.inventory.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

import com.dawn.microservice.inventory.dto.ProductDto;

import groovy.util.logging.Slf4j;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@Slf4j
public interface ProductClient {
    Logger log = LoggerFactory.getLogger(ProductClient.class);

    @GetExchange("/api/products")
    @CircuitBreaker(name = "product", fallbackMethod = "fallbackProduct")
    @Retry(name = "product")
    ProductDto getProductBySkuId(@PathVariable("skuId") String skuCode);

    default ProductDto fallbackMethod(String skuId, Throwable throwable) {
	log.info("Cannot get product for skuId {}, failure reason: {}", skuId, throwable.getMessage());
	return new ProductDto();
    }
}

package com.dawn.microservice.inventory.service;

import org.springframework.stereotype.Service;

import com.dawn.microservice.inventory.repository.InventoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public boolean isInStock(String skuCode, Integer quantity) {
//	Find an inventory for a given skuCode where quantity >= 0
	return inventoryRepository.existsBySkuCodeAndQuantityIsGreaterThanEqual(skuCode, quantity);
    }
}

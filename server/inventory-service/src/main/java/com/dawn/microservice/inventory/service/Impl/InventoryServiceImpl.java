package com.dawn.microservice.inventory.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dawn.microservice.inventory.model.Inventory;
import com.dawn.microservice.inventory.repository.InventoryRepository;
import com.dawn.microservice.inventory.service.InventoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private final InventoryRepository inventoryRepository;

    public boolean isInStock(String skuCode, Integer quantity) {
//	Find an inventory for a given skuCode where quantity >= 0
	log.info(" Start -- Received request to check stock for skuCode {}, with quantity {}", skuCode, quantity);
	boolean isInStock = inventoryRepository.existsBySkuCodeAndQuantityIsGreaterThanEqual(skuCode, quantity);
	log.info(" End -- Product with skuCode {}, and quantity {}, is in stock - {}", skuCode, quantity, isInStock);
	return isInStock;
    }

    @Override
    public List<Inventory> findAll() {
	return inventoryRepository.findAll();
    }

    @Override
    public Inventory findById(Long inventoryId) {
	return inventoryRepository.findById(inventoryId).orElseThrow(() -> new RuntimeException("Inventory not found"));
    }

    @Override
    public Inventory save(Inventory inventory) {
	return inventoryRepository.save(inventory);
    }

    @Override
    public Inventory update(Long inventoryId, Inventory inventory) {
	var checkExisting = inventoryRepository.findById(inventoryId)
		.orElseThrow(() -> new RuntimeException("Inventory not found"));

	return inventoryRepository.save(inventory);
    }

    @Override
    public void deleteById(Long inventoryId) {
	inventoryRepository.deleteById(inventoryId);

    }

}

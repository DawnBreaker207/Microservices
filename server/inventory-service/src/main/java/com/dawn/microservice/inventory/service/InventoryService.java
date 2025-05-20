package com.dawn.microservice.inventory.service;

import java.util.List;

import com.dawn.microservice.inventory.model.Inventory;

public interface InventoryService {
    List<Inventory> findAll();

    Inventory findById(final Long inventoryId);

    Inventory save(final Inventory inventory);

    Inventory update(final Long inventoryId, final Inventory inventory);

    void deleteById(final Long inventoryId);

    boolean isInStock(String skuCode, Integer quantity);

}

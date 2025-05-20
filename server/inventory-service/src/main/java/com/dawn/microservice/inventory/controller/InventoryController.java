package com.dawn.microservice.inventory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dawn.microservice.inventory.model.Inventory;
import com.dawn.microservice.inventory.service.InventoryService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
@Slf4j
public class InventoryController {
    @Autowired
    private final InventoryService inventoryService;

    @GetMapping("/checkStock")
    public ResponseEntity<Boolean> isInStock(@RequestParam String skuCode, @RequestParam Integer quantity) {
	log.info("Boolean, controller, check product is stock in inventory");
	return ResponseEntity.ok(inventoryService.isInStock(skuCode, quantity));
    }

    @GetMapping
    public ResponseEntity<List<Inventory>> findAll() {
	log.info("Inventory list, controller, fetch all inventory");
	return ResponseEntity.ok(inventoryService.findAll());
    }

    @GetMapping("{inventoryId}")
    public ResponseEntity<Inventory> findById(
	    @PathVariable("inventoryId") @NotBlank(message = "Import must not be blank") @Valid final String inventoryId) {
	log.info("Inventory, controller, fetch inventory by id");
	return ResponseEntity.ok(inventoryService.findById(Long.parseLong(inventoryId)));

    }

    @PostMapping
    public ResponseEntity<Inventory> save(
	    @RequestBody @NotNull(message = "Import must not be null") @Valid final Inventory inventory) {
	log.info("Inventory, controller, save inventory");
	return ResponseEntity.ok(inventoryService.save(inventory));
    }

    @PutMapping("{inventoryId}")
    public ResponseEntity<Inventory> update(
	    @PathVariable("inventoryId") @NotBlank(message = "Import must not be blank") @Valid final String inventoryId,
	    @RequestBody @NotNull(message = "Import must not be null") @Valid final Inventory inventory) {
	log.info("Inventory, controller, update inventory");
	return ResponseEntity.ok(inventoryService.update(Long.parseLong(inventoryId), inventory));
    }

    @DeleteMapping("{inventoryId}")
    public ResponseEntity<Boolean> deleteById(
	    @PathVariable("inventoryId") @NotBlank(message = "Import must not be blank") @Valid final String inventoryId) {
	log.info("Void, controller, delete inventory with inventoryId");
	inventoryService.deleteById(Long.parseLong(inventoryId));

	return ResponseEntity.ok(true);
    }

}

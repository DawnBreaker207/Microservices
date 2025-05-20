package com.dawn.microservice.productservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dawn.microservice.productservice.model.Product;
import com.dawn.microservice.productservice.service.ProductService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
	log.info("Product List, controller, fetch all products");
	return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("{productId}")
    public ResponseEntity<Product> findById(
	    @PathVariable("productId") @NotBlank(message = "Import must not be blank") @Valid final String productId) {
	log.info("Product, controller, fetch product by id");
	return ResponseEntity.ok(productService.findById(Long.parseLong(productId)));
    }

    @PostMapping
    public ResponseEntity<Product> save(
	    @RequestBody @NotNull(message = "Import must not be null") @Valid final Product product) {
	log.info("Product, controller, save product");
	return ResponseEntity.ok(productService.save(product));
    }

    @PutMapping("{productId}")
    public ResponseEntity<Product> update(
	    @PathVariable("productId") @NotBlank(message = "Import must not be blank") @Valid final String productId,
	    @RequestBody @NotNull(message = "Import must not be null") @Valid final Product product) {
	log.info("Product, controller, update product with productId");
	return ResponseEntity.ok(productService.update(Long.parseLong(productId), product));
    }

    @DeleteMapping("{productId}")
    public ResponseEntity<Boolean> deleteById(
	    @PathVariable("productId") @NotBlank(message = "Import must not be blank") @Valid final String productId) {
	log.info("Void, controller, delete product with productId");
	productService.deleteById(Long.parseLong(productId));

	return ResponseEntity.ok(true);

    }

}

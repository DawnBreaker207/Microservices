package com.dawn.microservice.productservice.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dawn.microservice.productservice.model.Product;
import com.dawn.microservice.productservice.repository.ProductRepository;
import com.dawn.microservice.productservice.service.ProductService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Transactional
@Slf4j
@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private final ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
	 log.info("List Product Service, delete category by id");
	return this.productRepository.findAll();
    }

    @Override
    public Product findById(Long productId) {
	 log.info("Product Service, delete category by id");
	return this.productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public Product save(Product product) {
	 log.info("Product Service, delete category by id");
	return this.productRepository.save(product);
    }

    @Override
    public Product update(Long productId, Product product) {
	 log.info("Product Service, delete category by id");
	Product existingProduct = productRepository.findById(productId)
		.orElseThrow(() -> new RuntimeException("Product not found"));

	return productRepository.save(product);
    }

    @Override
    public void deleteById(Long productId) {
	 log.info("Void Service, delete category by id");
	productRepository.deleteById(productId);

    }

}

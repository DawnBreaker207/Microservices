package com.dawn.microservice.productservice.service;

import java.util.List;

import com.dawn.microservice.productservice.model.Product;

public interface ProductService  {
    List<Product> findAll();

    Product findById(final Long productId);

    Product save(final Product product);

    Product update(final Long productId, final Product product);

    void deleteById(final Long productId);
}

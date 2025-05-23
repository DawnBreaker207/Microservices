package com.dawn.microservice.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dawn.microservice.productservice.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}

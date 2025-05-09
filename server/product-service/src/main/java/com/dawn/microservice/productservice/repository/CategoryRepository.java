package com.dawn.microservice.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dawn.microservice.productservice.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

}

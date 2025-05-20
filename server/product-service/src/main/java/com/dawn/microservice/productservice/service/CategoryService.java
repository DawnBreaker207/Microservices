package com.dawn.microservice.productservice.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.dawn.microservice.productservice.model.Category;

public interface CategoryService {
    List<Category> findAll();
    
    
    Page<Category> findAllCategory(int page, int size);
    List<Category> getAllCategories(Integer pageNo, Integer pageSize, String sortBy);

    Category findById(final Long categoryId);

    Category save(final Category category);

    Category update(final Long categoryId, final Category category);

    void deleteById(final Long categoryId);
}

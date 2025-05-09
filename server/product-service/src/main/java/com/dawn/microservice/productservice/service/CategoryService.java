package com.dawn.microservice.productservice.service;

import java.util.List;

import com.dawn.microservice.productservice.model.Category;

public interface CategoryService {
    List<Category> findAll();

    Category findById(final Long categoryId);

    Category save(final Category category);

    Category update(final Long categoryId, final Category category);

    void deleteById(final Long categoryId);
}

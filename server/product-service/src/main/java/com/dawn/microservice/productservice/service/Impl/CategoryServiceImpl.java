package com.dawn.microservice.productservice.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.dawn.microservice.productservice.model.Category;
import com.dawn.microservice.productservice.repository.CategoryRepository;
import com.dawn.microservice.productservice.service.CategoryService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Transactional
@Slf4j
@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
	log.info("List Category Service, fetch all category");
	return categoryRepository.findAll();
    }
    
    @Override
    public Category findById(Long id) {
       log.info("Category Service, fetch category by id");
        return categoryRepository.findById(id).orElseThrow(()-> new RuntimeException("Category not found"));
    }
    
    @Override
    public Category save(Category category) {
       log.info("Category Service, save category");
        return categoryRepository.save(category);
    }
    
    @Override
    public Category update(Long categoryId, Category category) {
	log.info("Category Service, update category");
        Category existingCategory = categoryRepository.findById(categoryId).orElseThrow(()-> new RuntimeException("Category not found"));
        
      return  categoryRepository.save(category);
    }
    
    @Override
    public void deleteById(Long categoryId) {
        log.info("Void Service, delete category by id");
        categoryRepository.deleteById(categoryId);
        
    }

    @Override
    public Page<Category> findAllCategory(int page, int size) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<Category> getAllCategories(Integer pageNo, Integer pageSize, String sortBy) {
	// TODO Auto-generated method stub
	return null;
    }
    
    
}

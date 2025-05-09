package com.dawn.microservice.productservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dawn.microservice.productservice.model.Category;
import com.dawn.microservice.productservice.service.CategoryService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private final CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<List<Category>> findAll() {
	log.info("Category List, controller, fetch all categories");
	return ResponseEntity.ok(categoryService.findAll());
    }

    @GetMapping("{categoryId}")
    public ResponseEntity<Category> findById(
	    @PathVariable("categoryId") @NotBlank(message = "Input must not be blank") @Valid final String categoryId) {
	log.info("Category, fetch category by id");
	return ResponseEntity.ok(categoryService.findById(Long.parseLong(categoryId)));
    }

    @PostMapping
    public ResponseEntity<Category> save(
	    @RequestBody @NotNull(message = "Input must not be NULL") @Valid final Category category) {
	log.info("Category, save category");
	return ResponseEntity.ok(categoryService.save(category));
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Category> update(
	    @PathVariable("categoryId") @NotBlank(message = "Input must not be blank") @Valid final String categoryId,
	    @RequestBody @NotNull(message = "Input must not be NULL") @Valid final Category category) {
	log.info("Category, update category with categoryId");
	return ResponseEntity.ok(categoryService.update(Long.parseLong(categoryId), category));
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("categoryId") final String categoryId) {
	log.info("Boolean, delete category by id");
	categoryService.deleteById(Long.parseLong(categoryId));
	return ResponseEntity.ok(true);
    }
}

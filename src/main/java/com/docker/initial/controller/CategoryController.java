package com.docker.initial.controller;

import com.docker.initial.modal.exam.Category;
import com.docker.initial.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        var addedCategory = this.categoryService.addCategory(category);
        //log.info("Category Added Successfully - {}", category.toString());
        return ResponseEntity.ok(addedCategory);
    }

    @GetMapping("/{categoryId}")
    public Category getCategory(@PathVariable("categoryId") Long categoryId) {
        var category = this.categoryService.getCategory(categoryId);
        //log.info("Category with id ");
        return category;
    }

    @GetMapping("/")
    public ResponseEntity<Set<Category>> getCategories() {
        Set<Category> categories = this.categoryService.getCategories();
        log.info("Categories Are - {}", categories);
        return ResponseEntity.ok(categories);
    }

    @PutMapping("/")
    public Category updateCategory(@RequestBody Category category) {
        return this.categoryService.updateCategory(category);
    }

    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable("categoryId") Long categoryId) {
        this.categoryService.deleteCategory(categoryId);
    }
}

package com.springboot.blog.controller;

import com.springboot.blog.dto.CategoryDto;
import com.springboot.blog.dto.PostDto;
import com.springboot.blog.model.Category;
import com.springboot.blog.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blog/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<CategoryDto>createCategory(@RequestBody CategoryDto categoryDto){
        CategoryDto category = categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }
    @GetMapping
    ResponseEntity<List<CategoryDto>> getAllCategories(){
        return ResponseEntity.ok(categoryService.getAllCategory());
    }
    @GetMapping("/{id}")
    ResponseEntity<CategoryDto> getCategoryById(@PathVariable(name = "id") long  categoryId){
        return ResponseEntity.ok(categoryService.getCategoryId(categoryId));
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<CategoryDto> updateCategory(@PathVariable(name = "id")long categoryId , @RequestBody CategoryDto categoryDto){
        CategoryDto categoryResponse = categoryService.updateCategory(categoryId, categoryDto);
        return ResponseEntity.ok(categoryResponse);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<String> deleteCategoryById(@PathVariable(name = "id") long  categoryId){
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok("Category Deleted Successfully");
    }



}

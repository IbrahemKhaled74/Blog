package com.springboot.blog.service;

import com.springboot.blog.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto getCategoryId(long categoryId);
    CategoryDto updateCategory(long categoryId,CategoryDto category);
    List<CategoryDto>getAllCategory();
    void deleteCategory(long categoryId);

}

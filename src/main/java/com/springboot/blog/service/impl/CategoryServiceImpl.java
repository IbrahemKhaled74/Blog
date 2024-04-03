package com.springboot.blog.service.impl;

import com.springboot.blog.dto.CategoryDto;
import com.springboot.blog.dto.PostDto;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.model.Category;
import com.springboot.blog.model.Post;
import com.springboot.blog.repository.CategoryRepo;
import com.springboot.blog.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepo categoryRepo;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepo categoryRepo, ModelMapper modelMapper) {
        this.categoryRepo = categoryRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = mapToCategory(categoryDto);
        Category savedCategory = categoryRepo.save(category);

        return mapToCategoryDto(savedCategory);
    }

    @Override
    public CategoryDto getCategoryId(long categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        return mapToCategoryDto(category);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        return categoryRepo.findAll().stream().map(this::mapToCategoryDto).collect(Collectors.toList());
    }

    @Override
    public CategoryDto updateCategory(long categoryId, CategoryDto categoryDto) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        category.setId(categoryDto.getId());
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        categoryRepo.save(category);

        return mapToCategoryDto(category);
    }

    @Override
    public void deleteCategory(long categoryId) {
        if (categoryRepo.findById(categoryId).isPresent()){
            categoryRepo.deleteById(categoryId);
        }

    }
    private CategoryDto mapToCategoryDto(Category category){
        return modelMapper.map(category,CategoryDto.class);
    }
    private Category mapToCategory(CategoryDto categoryDto){
        return modelMapper.map(categoryDto,Category.class);
    }

}

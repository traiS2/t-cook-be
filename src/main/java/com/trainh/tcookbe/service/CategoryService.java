package com.trainh.tcookbe.service;

import com.trainh.tcookbe.dto.category.CategoryDto;
import com.trainh.tcookbe.payload.request.category.CreateCategoriesForBlogRequest;
import com.trainh.tcookbe.payload.request.category.CreateCategoryRequest;

import java.util.List;

public interface CategoryService {
    public String createCategory(CreateCategoryRequest request);
    public String createCategoriesForBlog(CreateCategoriesForBlogRequest request);
    public List<CategoryDto> getAllCategoryByActiveStatus();
}

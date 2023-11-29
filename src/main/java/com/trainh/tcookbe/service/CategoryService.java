package com.trainh.tcookbe.service;

import com.trainh.tcookbe.payload.request.category.CreateCategoriesForBlogRequest;
import com.trainh.tcookbe.payload.request.category.CreateCategoryRequest;
import org.springframework.stereotype.Service;

public interface CategoryService {
    public String createCategory(CreateCategoryRequest request);
    public String createCategoriesForBlog(CreateCategoriesForBlogRequest request);
}

package com.trainh.tcookbe.serviceImpl;

import com.trainh.tcookbe.model.entity.Blog;
import com.trainh.tcookbe.model.entity.Category;
import com.trainh.tcookbe.model.entity.Status;
import com.trainh.tcookbe.model.enums.EStatus;
import com.trainh.tcookbe.payload.request.category.CreateCategoriesForBlogRequest;
import com.trainh.tcookbe.payload.request.category.CreateCategoryRequest;
import com.trainh.tcookbe.repository.BlogRepository;
import com.trainh.tcookbe.repository.CategoryRepository;
import com.trainh.tcookbe.repository.StatusRepository;
import com.trainh.tcookbe.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final StatusRepository statusRepository;
    private final BlogRepository blogRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, StatusRepository statusRepository, BlogRepository blogRepository) {
        this.categoryRepository = categoryRepository;
        this.statusRepository = statusRepository;
        this.blogRepository = blogRepository;
    }

    @Override
    public String createCategory(CreateCategoryRequest request) {
        try {
            Optional<Status> statusOptional = statusRepository.findByName(EStatus.HIDE);
            if(statusOptional.isPresent()) {
                Category category = new Category(request.getName().trim(), statusOptional.get());
            } else {
                return "Create category failed";
            }
            Category category = new Category(request.getName().trim(), statusOptional.get());
            categoryRepository.save(category);
        } catch (Exception e) {
            return "Create category failed";
        }
        return null;
    }

    @Override
    public String createCategoriesForBlog(CreateCategoriesForBlogRequest request) {
        try {
            int blogId = request.getBlogId();
            List<Integer> categories = request.getCategories();
            Optional<Blog> blogOptional = blogRepository.findById(blogId);
            if(blogOptional.isPresent()) {
                Blog blog = blogOptional.get();
                Set<Category> categorySet = blog.getCategories();
                for(int categoryId : categories) {
                    Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
                    Category category;
                    if(categoryOptional.isPresent()) {
                        category = categoryOptional.get();
                    } else {
                        return "Category not found";
                    }
                    categorySet.add(category);
                }
                blog.setCategories(categorySet);
                blogRepository.save(blog);
            } else {
                return "Blog not found";
            }

        } catch (Exception e) {
            return "Create categories for blog failed";
        }
        return null;
    }
}

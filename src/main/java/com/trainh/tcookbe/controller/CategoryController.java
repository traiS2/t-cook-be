package com.trainh.tcookbe.controller;

import com.trainh.tcookbe.config.GlobalExceptionHandler;
import com.trainh.tcookbe.model.dto.category.CategoryDto;
import com.trainh.tcookbe.payload.request.category.CreateCategoriesForBlogRequest;
import com.trainh.tcookbe.payload.request.category.CreateCategoryRequest;
import com.trainh.tcookbe.payload.response.MessageResponse;
import com.trainh.tcookbe.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "${trainh.app.cross-origin}")
@RestController
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService categoryService;
    private final GlobalExceptionHandler validationExceptionHandler;

    @Autowired
    public CategoryController(CategoryService categoryService, GlobalExceptionHandler validationExceptionHandler) {
        this.categoryService = categoryService;
        this.validationExceptionHandler = validationExceptionHandler;
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    @GetMapping("/get-all-category-by-show-status")
    public ResponseEntity<?> getAllCategoryByActiveStatus() {
        List<CategoryDto> categories = categoryService.getAllCategoryByShowStatus();
        if (categories != null) {
            return ResponseEntity.ok(categories);
        } else {
            return ResponseEntity.badRequest().body(new MessageResponse("Error some thing!"));
        }
    }

    @PostMapping("/create-category")
    public ResponseEntity<?> createCategory(@Valid @RequestBody CreateCategoryRequest request, BindingResult bindingResult) {
        String errorMessage = validationExceptionHandler.getFirstErrorMessage(bindingResult);
        if (errorMessage != null) {
            return ResponseEntity.badRequest().body(errorMessage);
        } else {
            String message = categoryService.createCategory(request);
            if (message != null) {
                return ResponseEntity.badRequest().body(new MessageResponse(message));
            } else {
                return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse("Create category successfully"));
            }
        }
    }

    @PostMapping("/create-categories-for-blog")
    public ResponseEntity<?> createCategoriesForBlog(@Valid @RequestBody CreateCategoriesForBlogRequest request, BindingResult bindingResult) {
        String errorMessage = validationExceptionHandler.getFirstErrorMessage(bindingResult);
        if (errorMessage != null) {
            return ResponseEntity.badRequest().body(errorMessage);
        } else {
            String message = categoryService.createCategoriesForBlog(request);
            if (message != null) {
                return ResponseEntity.badRequest().body(new MessageResponse(message));
            } else {
                return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse("Create categories for blog successfully"));
            }
        }
    }
}

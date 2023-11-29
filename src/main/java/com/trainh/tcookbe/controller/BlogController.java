package com.trainh.tcookbe.controller;

import com.trainh.tcookbe.config.GlobalExceptionHandler;
import com.trainh.tcookbe.payload.request.blog.CreateBlogRequest;
import com.trainh.tcookbe.payload.response.MessageResponse;
import com.trainh.tcookbe.service.BlogService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "${trainh.app.cross-origin}", maxAge = 3600)
@RestController
@RequestMapping("/api/blog")
public class BlogController {
    private final BlogService blogService;
    private final GlobalExceptionHandler validationExceptionHandler;

    @Autowired
    public BlogController(BlogService blogService, GlobalExceptionHandler validationExceptionHandler) {
        this.blogService = blogService;
        this.validationExceptionHandler = validationExceptionHandler;
    }

    @PostMapping("/create-blog")
    public ResponseEntity<?> createBlog(@Valid @RequestBody CreateBlogRequest request, BindingResult bindingResult) {
        String errorMessage = validationExceptionHandler.getFirstErrorMessage(bindingResult);
        if(errorMessage != null) {
            return ResponseEntity.badRequest().body(new MessageResponse(errorMessage));
        } else {
            String message = blogService.createBlog(request);
            if(message != null) {
                return ResponseEntity.badRequest().body(new MessageResponse(message));
            } else {
                return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse("Create blog successfully"));
            }
        }
    }
}

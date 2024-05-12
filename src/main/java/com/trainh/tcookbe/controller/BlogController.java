package com.trainh.tcookbe.controller;

import com.trainh.tcookbe.config.GlobalExceptionHandler;
import com.trainh.tcookbe.model.dto.blog.BriefBlogDTO;
import com.trainh.tcookbe.model.dto.blog.DetailBlogDTO;
import com.trainh.tcookbe.model.dto.blog.SummaryBlogDTO;
import com.trainh.tcookbe.model.entity.Blog;
import com.trainh.tcookbe.payload.request.blog.BlogCreationRequest;
import com.trainh.tcookbe.payload.response.MessageResponse;
import com.trainh.tcookbe.service.BlogService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    @PostMapping("/create-blog")
    public ResponseEntity<?> createBlog(@Valid @RequestBody BlogCreationRequest request, BindingResult bindingResult) {
        String errorMessage = validationExceptionHandler.getFirstErrorMessage(bindingResult);
        if (errorMessage != null) {
            return ResponseEntity.badRequest().body(new MessageResponse(errorMessage));
        } else {
            String message = blogService.createBlog(request);
            if (message != null) {
                return ResponseEntity.badRequest().body(new MessageResponse(message));
            } else {
                return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse("Create blog successfully"));
            }
        }
    }

    @GetMapping("/get-brief-blog")
    public ResponseEntity<?> getBriefBlog() {
        try {
            List<BriefBlogDTO> briefBlogs = blogService.getBriefBlog();
            if (briefBlogs != null) {
                return ResponseEntity.ok(briefBlogs);
            } else {
                return ResponseEntity.badRequest().body(new MessageResponse("Error some thing!"));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error some thing!"));
        }
    }

    @GetMapping("/get-summary-blog")
    public ResponseEntity<?> getSummaryBlog() {
        try {
            List<SummaryBlogDTO> blogSummaries = blogService.getSummaryBlog();
            if (blogSummaries != null) {
                return ResponseEntity.ok(blogSummaries);
            } else {
                return ResponseEntity.badRequest().body(new MessageResponse("Error some thing!"));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error some thing!"));
        }
    }

    @GetMapping("/get-detail-blog/{link}")
    public ResponseEntity<?> getDetailBlog(@PathVariable("link") String link) {
        try {
            Optional<DetailBlogDTO> blog = blogService.getDetailBlog(link.toLowerCase().trim());
            if (blog.isPresent()) {
                return ResponseEntity.ok(blog);
            } else {
                return ResponseEntity.badRequest().body(new MessageResponse("Error some thing!"));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error some thing!"));
        }
    }

    @GetMapping("/get-all-blog")
    public ResponseEntity<?> getAllBlog() {
        try {
            Optional<Blog> blogs = blogService.getAllBlog();
            if (blogs.isPresent()) {
                return ResponseEntity.ok(blogs);
            } else {
                return ResponseEntity.badRequest().body(new MessageResponse("Error some thing!"));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error some thing!"));
        }
    }
}

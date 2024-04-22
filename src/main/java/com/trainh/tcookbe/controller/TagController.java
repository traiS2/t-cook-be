package com.trainh.tcookbe.controller;

import com.trainh.tcookbe.config.GlobalExceptionHandler;
import com.trainh.tcookbe.model.dto.tag.TagDto;
import com.trainh.tcookbe.model.dto.tag.TagSummaryDto;
import com.trainh.tcookbe.model.projection.tag.TagSummaryProjection;
import com.trainh.tcookbe.payload.request.tag.CreateTagRequest;
import com.trainh.tcookbe.payload.request.tag.CreateTagsForBlogRequest;
import com.trainh.tcookbe.payload.response.MessageResponse;
import com.trainh.tcookbe.service.TagService;
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
@RequestMapping("/api/tag")
public class TagController {
    private final TagService tagService;
    private final GlobalExceptionHandler validationExceptionHandler;

    @Autowired
    public TagController(TagService tagService, GlobalExceptionHandler validationExceptionHandler) {
        this.tagService = tagService;
        this.validationExceptionHandler = validationExceptionHandler;
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    @GetMapping("/get-all-tag")
    public ResponseEntity<?> getAllTags() {
        try {
            List<TagSummaryDto> tagSummaryDtoList = tagService.getAllTag();
            if(tagSummaryDtoList != null) {
                return ResponseEntity.ok(tagSummaryDtoList);
            } else {
                return ResponseEntity.badRequest().body(new MessageResponse("Get all tags failed"));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Get all tags failed"));
        }
    }


    @PostMapping("/create-tag")
    public ResponseEntity<?> createTag(@Valid @RequestBody CreateTagRequest request, BindingResult bindingResult) {
        String errorMessage = validationExceptionHandler.getFirstErrorMessage(bindingResult);
        if (errorMessage != null) {
            return ResponseEntity.badRequest().body(new MessageResponse(errorMessage));
        } else {
            String message = tagService.createTag(request);
            if (message != null) {
                return ResponseEntity.badRequest().body(new MessageResponse(message));
            } else {
                return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse("Create tag successfully"));
            }
        }
    }

    @PostMapping("/create-tags-for-blog")
    public ResponseEntity<?> createTagsForBlog(@Valid @RequestBody CreateTagsForBlogRequest request, BindingResult bindingResult) {
        String errorMessage = validationExceptionHandler.getFirstErrorMessage(bindingResult);
        if (errorMessage != null) {
            return ResponseEntity.badRequest().body(new MessageResponse(errorMessage));
        } else {
            String message = tagService.createTagsForBlog(request);
            if (message != null) {
                return ResponseEntity.badRequest().body(new MessageResponse(message));
            } else {
                return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse("Create tags for blog successfully"));
            }
        }
    }


}

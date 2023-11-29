package com.trainh.tcookbe.payload.request.category;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.util.List;

@Getter
public class CreateCategoriesForBlogRequest {
    @NotBlank(message = "Blog id is required")
    private int blogId;
    @NotBlank(message = "Categories are required")
    private List<Integer> categories;
}

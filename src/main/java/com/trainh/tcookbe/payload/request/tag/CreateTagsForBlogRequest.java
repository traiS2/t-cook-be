package com.trainh.tcookbe.payload.request.tag;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.util.List;

@Getter
public class CreateTagsForBlogRequest {
    @NotBlank(message = "Blog id is required")
    private int blogId;
    @NotBlank(message = "Tags are required")
    private List<Integer> tagsId;
}

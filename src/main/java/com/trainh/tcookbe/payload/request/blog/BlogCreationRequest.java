package com.trainh.tcookbe.payload.request.blog;

import com.trainh.tcookbe.payload.request.recipe.RecipeBlogCreationRequest;
import com.trainh.tcookbe.payload.request.tag.TagBlogCreationRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BlogCreationRequest {
    private String title;
    private List<Integer> category;
    private String image;
    private List<String> introduction;
    private int levelOfDifficult;
    private int cookingTime;
    private String servingSize;
    private List<String> ingredient;
    private List<RecipeBlogCreationRequest> recipe;
    private List<TagBlogCreationRequest> tags;
    private long userId;
}

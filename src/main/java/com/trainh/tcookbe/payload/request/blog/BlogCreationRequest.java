package com.trainh.tcookbe.payload.request.blog;

import com.trainh.tcookbe.payload.request.ingredient.IngredientBlogCreationRequest;
import com.trainh.tcookbe.payload.request.introduction.IntroductionBlogCreationRequest;
import com.trainh.tcookbe.payload.request.recipe.RecipeBlogCreationRequest;
import com.trainh.tcookbe.payload.request.tag.TagBlogCreationRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BlogCreationRequest {
    private String title;
    private List<Integer> category;
    private String image;
    private List<IntroductionBlogCreationRequest> introduction;
    private int levelOfDifficult;
    private int cookingTime;
    private String servingSize;
    private List<IngredientBlogCreationRequest> ingredient;
    private List<RecipeBlogCreationRequest> recipe;
    private List<TagBlogCreationRequest> tag;
    private long userId;
}

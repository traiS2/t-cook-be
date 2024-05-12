package com.trainh.tcookbe.model.projection.recipe;


import java.util.List;

public interface RecipeProjection {
    long getId();
    String getName();
    String getImage();
    List<DetailRecipeProjection> getDetailRecipe();
}

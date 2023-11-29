package com.trainh.tcookbe.service;

import com.trainh.tcookbe.payload.request.recipe.CreateRecipeRequest;

public interface RecipeService {
    public String createRecipe(CreateRecipeRequest request);
}

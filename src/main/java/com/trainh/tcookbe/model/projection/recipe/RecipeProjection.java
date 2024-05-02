package com.trainh.tcookbe.model.projection.recipe;

import java.util.List;

public interface RecipeProjection {
    long getId();
    String getName();
    int getStep();
    String getImage();
    List<RecipeDetailsProjection> getDetailsRecipe();
}

package com.trainh.tcookbe.model.projection.blog;

import com.trainh.tcookbe.model.projection.ingredient.IngredientProjection;
import com.trainh.tcookbe.model.projection.introduction.IntroductionProjection;
import com.trainh.tcookbe.model.projection.recipe.RecipeProjection;
import com.trainh.tcookbe.model.projection.user.UserFullNameProjection;

import java.util.Date;
import java.util.List;

public interface DetailBlogProjection {
    long getId();
    String getLink();
    String getName();
    String getImage();
    int getLevelOfDifficult();
    int getCookingTime();
    String getServingSize();
    Date getCreateAt();
    UserFullNameProjection getUser();
    List<IntroductionProjection> getIntroduction();
    List<RecipeProjection> getRecipe();
    List<IngredientProjection> getIngredient();
}

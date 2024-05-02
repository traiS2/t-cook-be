package com.trainh.tcookbe.model.projection.blog;

import com.trainh.tcookbe.model.projection.tag.TagNameProjection;
import com.trainh.tcookbe.model.projection.introduction.IntroductionContentProjection;
import com.trainh.tcookbe.model.projection.recipe.RecipeProjection;
import com.trainh.tcookbe.model.projection.user.UserFullNameProjection;

import java.util.Date;
import java.util.List;

public interface BlogDetailsProjection {
    long getId();
    String getLink();
    String getName();
    String getImage();
    int getLevelOfDifficult();
    int getCookingTime();
    String getServingSize();
    Date getCreateAt();
    UserFullNameProjection getUser();
    List<IntroductionContentProjection> getIntroduction();
//    List<TagNameProjection> getTags();
    List<RecipeProjection> getRecipes();
}

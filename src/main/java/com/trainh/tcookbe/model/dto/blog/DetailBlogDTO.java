package com.trainh.tcookbe.model.dto.blog;

import com.trainh.tcookbe.model.dto.ingredient.IngredientDTO;
import com.trainh.tcookbe.model.dto.introduction.IntroductionDTO;
import com.trainh.tcookbe.model.dto.recipe.RecipeDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class DetailBlogDTO {
    private long id;
    private String link;
    private String name;
    private String image;
    private int levelOfDifficult;
    private int cookingTime;
    private String servingSize;
    private Date createAt;
    private List<IngredientDTO> ingredient;
    private List<IntroductionDTO> introduction;
//    private List<String> tags;
    private String user;
    private List<RecipeDTO> recipe;
}

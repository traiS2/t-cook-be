package com.trainh.tcookbe.model.dto.recipe;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecipeDTO {
    private long id;
    private String name;
    private String image;
    private List<DetailRecipeDTO> detailRecipe;
}

package com.trainh.tcookbe.model.dto.recipe;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecipeDto {
    private long id;
    private String name;
    private int step;
    private String image;
    private RecipeDetailsDto details;
}

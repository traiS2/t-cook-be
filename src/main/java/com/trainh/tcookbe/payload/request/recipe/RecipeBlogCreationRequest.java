package com.trainh.tcookbe.payload.request.recipe;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RecipeBlogCreationRequest {
    private String name;
    private List<String> detailsRecipe;
    private String image;
}

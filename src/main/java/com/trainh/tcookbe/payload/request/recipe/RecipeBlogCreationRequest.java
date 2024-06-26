package com.trainh.tcookbe.payload.request.recipe;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RecipeBlogCreationRequest {
    private long id;
    private String name;
    private List<DetailRecipeBlogCreationRequest> detailRecipe;
    private String image;
}

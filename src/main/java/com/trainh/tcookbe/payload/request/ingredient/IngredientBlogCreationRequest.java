package com.trainh.tcookbe.payload.request.ingredient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class IngredientBlogCreationRequest {
    private long id;
    private String name;
}

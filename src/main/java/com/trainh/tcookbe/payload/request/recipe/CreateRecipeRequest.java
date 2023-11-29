package com.trainh.tcookbe.payload.request.recipe;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateRecipeRequest {
    @NotBlank(message = "Title must not be blank")
    String title;
    @Min(value = 1, message = "Step must be greater than 0")
    private int step;
    private int blogId;
    @NotBlank(message = "Description must not be blank")
    private String description;
    private String image;
}

package com.trainh.tcookbe.payload.request.category;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateCategoryRequest {
    @NotBlank(message = "Category name is required")
    private String name;
}

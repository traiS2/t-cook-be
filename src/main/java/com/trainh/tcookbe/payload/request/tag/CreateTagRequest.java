package com.trainh.tcookbe.payload.request.tag;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateTagRequest {
    @NotBlank(message = "Tag name is required")
    private String name;
}

package com.trainh.tcookbe.payload.request.recipe;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DetailRecipeBlogCreationRequest {
    private long id;
    private String content;
}

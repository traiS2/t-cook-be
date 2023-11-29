package com.trainh.tcookbe.payload.request.blog;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateBlogRequest {
    private String name;
    private String image;
    private String introduction;
    private int levelOfDifficult;
    private int cookingTime;
    private String servingSize;
    private int userId;
}   
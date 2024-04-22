package com.trainh.tcookbe.model.dto.blog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BlogDto {
    private int id;
    private String link;
    private String name;
    private String image;
    private int levelOfDifficult;
    private int cookingTime;
    private String servingSize;
}

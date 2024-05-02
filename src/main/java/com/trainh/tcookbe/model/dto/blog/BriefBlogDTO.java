package com.trainh.tcookbe.model.dto.blog;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BriefBlogDTO {
    private long id;
    private String link;
    private String name;
    private String image;
}

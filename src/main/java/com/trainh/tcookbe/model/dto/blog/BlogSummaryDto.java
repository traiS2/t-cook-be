package com.trainh.tcookbe.model.dto.blog;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BlogSummaryDto {
    private long id;
    private String link;
    private String name;
    private String image;
    private Date createAt;
    private List<String> introduction;
    private List<String> tags;
    private String user;
}

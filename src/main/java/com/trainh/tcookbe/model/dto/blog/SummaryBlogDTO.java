package com.trainh.tcookbe.model.dto.blog;

import com.trainh.tcookbe.model.dto.introduction.IntroductionDTO;
import com.trainh.tcookbe.model.dto.tag.TagDTO;
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
public class SummaryBlogDTO {
    private long id;
    private String link;
    private String name;
    private String image;
    private Date createAt;
    private List<IntroductionDTO> introduction;
    private List<TagDTO> tags;
    private String user;
}

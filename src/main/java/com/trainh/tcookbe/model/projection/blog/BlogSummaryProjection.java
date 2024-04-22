package com.trainh.tcookbe.model.projection.blog;


import com.trainh.tcookbe.model.dto.tag.TagNameProjection;
import com.trainh.tcookbe.model.projection.introduction.IntroductionContentProjection;
import com.trainh.tcookbe.model.projection.user.UserFullNameProjection;

import java.util.Date;
import java.util.List;

public interface BlogSummaryProjection {
    long getId();
    String getLink();
    String getName();
    String getImage();
    Date getCreateAt();
    List<IntroductionContentProjection> getIntroduction();
    List<TagNameProjection> getTags();
    UserFullNameProjection getUser();
}

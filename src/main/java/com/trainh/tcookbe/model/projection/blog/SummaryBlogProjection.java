package com.trainh.tcookbe.model.projection.blog;


import com.trainh.tcookbe.model.projection.introduction.IntroductionProjection;
import com.trainh.tcookbe.model.projection.tag.TagNameProjection;
import com.trainh.tcookbe.model.projection.user.UserFullNameProjection;

import java.util.Date;
import java.util.List;

public interface SummaryBlogProjection {
    long getId();
    String getLink();
    String getName();
    String getImage();
    Date getCreateAt();
    List<IntroductionProjection> getIntroduction();
    List<TagNameProjection> getTags();
    UserFullNameProjection getUser();
}

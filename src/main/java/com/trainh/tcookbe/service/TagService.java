package com.trainh.tcookbe.service;

import com.trainh.tcookbe.model.dto.tag.TagSummaryDto;
import com.trainh.tcookbe.payload.request.tag.CreateTagRequest;
import com.trainh.tcookbe.payload.request.tag.CreateTagsForBlogRequest;

import java.util.List;

public interface TagService {
    public String createTag(CreateTagRequest request);
    public String createTagsForBlog(CreateTagsForBlogRequest request);
    public List<TagSummaryDto> getAllTag();
}

package com.trainh.tcookbe.service;

import com.trainh.tcookbe.payload.request.tag.CreateTagRequest;
import com.trainh.tcookbe.payload.request.tag.CreateTagsForBlogRequest;

public interface TagService {
    public String createTag(CreateTagRequest request);
    public String createTagsForBlog(CreateTagsForBlogRequest request);
}

package com.trainh.tcookbe.service;

import com.trainh.tcookbe.model.dto.blog.BlogBriefDto;
import com.trainh.tcookbe.model.dto.blog.BlogSummaryDto;
import com.trainh.tcookbe.model.entity.Blog;
import com.trainh.tcookbe.model.projection.blog.BlogSummaryProjection;
import com.trainh.tcookbe.payload.request.blog.BlogCreationRequest;

import java.util.List;
import java.util.Optional;

public interface BlogService {
    public String blogCreation(BlogCreationRequest request);
    public Optional<Blog> getAllBlog();
    public List<BlogBriefDto> getBlogBrief();
    public List<BlogSummaryDto> getBlogSummary();
}

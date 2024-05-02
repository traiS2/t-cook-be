package com.trainh.tcookbe.service;

import com.trainh.tcookbe.model.dto.blog.BriefBlogDTO;
import com.trainh.tcookbe.model.dto.blog.SummaryBlogDTO;
import com.trainh.tcookbe.model.entity.Blog;
import com.trainh.tcookbe.payload.request.blog.BlogCreationRequest;

import java.util.List;
import java.util.Optional;

public interface BlogService {
    public String createBlog(BlogCreationRequest request);
    public Optional<Blog> getAllBlog();
    public List<BriefBlogDTO> getBriefBlog();
    public List<SummaryBlogDTO> getSummaryBlog();

}

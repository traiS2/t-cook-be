package com.trainh.tcookbe.service;

import com.trainh.tcookbe.payload.request.blog.CreateBlogRequest;
import org.springframework.stereotype.Service;

public interface BlogService {
    public String createBlog(CreateBlogRequest request);
}

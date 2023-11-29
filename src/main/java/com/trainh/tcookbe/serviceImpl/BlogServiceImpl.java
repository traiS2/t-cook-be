package com.trainh.tcookbe.serviceImpl;

import com.trainh.tcookbe.model.entity.Blog;
import com.trainh.tcookbe.model.entity.Status;
import com.trainh.tcookbe.model.entity.User;
import com.trainh.tcookbe.model.enums.EStatus;
import com.trainh.tcookbe.payload.request.blog.CreateBlogRequest;
import com.trainh.tcookbe.repository.BlogRepository;
import com.trainh.tcookbe.repository.StatusRepository;
import com.trainh.tcookbe.repository.UserRepository;
import com.trainh.tcookbe.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;
    private final StatusRepository statusRepository;
    private final UserRepository userRepository;

    @Autowired
    public BlogServiceImpl(BlogRepository blogRepository, StatusRepository statusRepository, UserRepository userRepository) {
        this.blogRepository = blogRepository;
        this.statusRepository = statusRepository;
        this.userRepository = userRepository;
    }

    @Override
    public String createBlog(CreateBlogRequest request) {
        try {
            Optional<Status> statusOptional = statusRepository.findByName(EStatus.PENDING);
            Optional<User> userOptional = userRepository.findById(request.getUserId());
            if (statusOptional.isPresent() && userOptional.isPresent()) {
                Blog blog = new Blog(request.getName().trim(),
                        request.getImage().trim(),
                        request.getIntroduction().trim(),
                        request.getLevelOfDifficult(),
                        request.getCookingTime(),
                        request.getServingSize().trim(),
                        statusOptional.get(),
                        new Date(),
                        userOptional.get()
                );
                blogRepository.save(blog);
            } else {
                return "Create blog failed";
            }
        } catch (Exception e) {
            return "Create blog failed";
        }
        return null;
    }
}

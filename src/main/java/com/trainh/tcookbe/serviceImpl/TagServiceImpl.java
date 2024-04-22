package com.trainh.tcookbe.serviceImpl;

import com.trainh.tcookbe.mapper.tag.TagSummaryMapper;
import com.trainh.tcookbe.model.dto.tag.TagDto;
import com.trainh.tcookbe.mapper.TagMapper;
import com.trainh.tcookbe.model.dto.tag.TagSummaryDto;
import com.trainh.tcookbe.model.entity.Blog;
import com.trainh.tcookbe.model.entity.Tag;
import com.trainh.tcookbe.payload.request.tag.CreateTagRequest;
import com.trainh.tcookbe.payload.request.tag.CreateTagsForBlogRequest;
import com.trainh.tcookbe.repository.BlogRepository;
import com.trainh.tcookbe.repository.TagRepository;
import com.trainh.tcookbe.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;
    private final BlogRepository blogRepository;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository, BlogRepository blogRepository) {
        this.tagRepository = tagRepository;
        this.blogRepository = blogRepository;
    }

    @Override
    public String createTag(CreateTagRequest request) {
        Tag tag = new Tag(request.getName().toLowerCase().trim());
        try {
            Optional<Tag> tagOptional = tagRepository.findByName(tag.getName());
            if (tagOptional.isPresent()) {
                return "Tag already exists";
            } else {
                tagRepository.save(tag);
            }
        } catch (Exception e) {
            return "Create tag failed";
        }
        return null;
    }

    @Override
    public String createTagsForBlog(CreateTagsForBlogRequest request) {
        try {
            int blogId = request.getBlogId();
            List<Integer> tagsId = request.getTagsId();
            Optional<Blog> blogOptional = blogRepository.findById(blogId);
            if (blogOptional.isPresent()) {
                Blog blog = blogOptional.get();
                Set<Tag> tagSet = blog.getTags();
                for (int tagId : tagsId) {
                    Optional<Tag> tagOptional = tagRepository.findById(tagId);
                    Tag tag;
                    if (tagOptional.isPresent()) {
                        tag = tagOptional.get();
                    } else {
                        return "Tag not found";
                    }
                    tagSet.add(tag);
                }
                blog.setTags(tagSet);
                blogRepository.save(blog);
            } else {
                return "Blog not found";
            }

        } catch (Exception e) {
            return "Create tags for blog failed";
        }
        return null;
    }

    @Override
    public List<TagSummaryDto> getAllTag() {
        try {
            List<Tag> tags = tagRepository.findAll();
            return
                    tags.stream().map(
                            TagSummaryMapper.INSTANCE::tagSummaryToDto
                    ).toList();
        } catch (Exception e) {
            return null;
        }
    }
}

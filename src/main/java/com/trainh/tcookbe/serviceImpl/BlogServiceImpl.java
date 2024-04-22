package com.trainh.tcookbe.serviceImpl;

import com.trainh.tcookbe.mapper.blog.BlogBriefMapper;
import com.trainh.tcookbe.mapper.blog.BlogSummaryMapper;
import com.trainh.tcookbe.model.dto.blog.BlogBriefDto;
import com.trainh.tcookbe.model.dto.blog.BlogSummaryDto;
import com.trainh.tcookbe.model.entity.*;
import com.trainh.tcookbe.model.enums.EStatus;
import com.trainh.tcookbe.model.projection.blog.BlogSummaryProjection;
import com.trainh.tcookbe.payload.request.blog.BlogCreationRequest;
import com.trainh.tcookbe.repository.BlogRepository;
import com.trainh.tcookbe.repository.StatusRepository;
import com.trainh.tcookbe.repository.TagRepository;
import com.trainh.tcookbe.repository.UserRepository;
import com.trainh.tcookbe.service.BlogService;
import com.trainh.tcookbe.utils.Normalized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;
    private final StatusRepository statusRepository;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;

    @Autowired
    public BlogServiceImpl(BlogRepository blogRepository, StatusRepository statusRepository, UserRepository userRepository, TagRepository tagRepository) {
        this.blogRepository = blogRepository;
        this.statusRepository = statusRepository;
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
    }

    @Override
    public String blogCreation(BlogCreationRequest request) {
        try {
            Optional<Status> statusOptional = statusRepository.findByName(EStatus.HIDE);
            Optional<User> userOptional = userRepository.findById(request.getUserId());
            if (statusOptional.isPresent() && userOptional.isPresent()) {
                String link = Normalized.normalize(request.getTitle());
                String name = request.getTitle().trim();
                String image = request.getImage().trim();
                int levelOfDifficult = request.getLevelOfDifficult();
                int cookingTime = request.getCookingTime();
                String servingSize = request.getServingSize().trim();
                Date createdAt = new Date();

                Set<Introduction> introductions = new HashSet<>();
                for (String introduction : request.getIntroduction()) {
                    introductions.add(new Introduction(introduction));
                }

                Set<Ingredient> ingredients = new HashSet<>();
                request.getIngredient().forEach(ingredient -> ingredients.add(new Ingredient(ingredient)));

                Set<Recipe> recipes = new HashSet<>();
                final int[] posRecipe = {1};
                request.getRecipe().forEach(
                        recipe -> {
                            HashSet<DetailsRecipe> detailsRecipes = new HashSet<>();
                            recipe.getDetailsRecipe().forEach(
                                    detailsRecipe -> {
                                        detailsRecipes.add(new DetailsRecipe(detailsRecipe));
                                    }
                            );
                            recipes.add(new Recipe(recipe.getName(), posRecipe[0], recipe.getImage(), detailsRecipes));
                            posRecipe[0]++;

                        }
                );

                Set<Category> categories = new HashSet<>();
                request.getCategory().forEach(category -> categories.add(new Category(category)));

                Set<Tag> tags = new HashSet<>();
                request.getTags().forEach(tag -> {
                    if (tag.isNew()) {
                        Tag newTag = new Tag(tag.getName().toLowerCase().trim());
                        Tag saveTag = tagRepository.saveAndFlush(newTag);
                        tags.add(new Tag(saveTag.getId()));
                    } else {
                        tags.add(new Tag(tag.getId()));
                    }
                });

                Blog blog = new Blog(link, name, image, levelOfDifficult, cookingTime, servingSize, userOptional.get(), statusOptional.get(), createdAt, introductions, ingredients, tags, recipes, categories);

                ingredients.forEach(ingredient -> ingredient.setBlog(blog));
                introductions.forEach(introduction -> introduction.setBlog(blog));
                  recipes.forEach(recipe -> {
                    recipe.setBlog(blog);
                    recipe.getDetailsRecipe().forEach(detailsRecipe -> detailsRecipe.setRecipe(recipe));
                });
                blogRepository.save(blog);
            } else {
                return "Create blog failed";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
        return null;
    }

    @Override
    public Optional<Blog> getAllBlog() {
        try {
            return blogRepository.findBlogByLink("chan-ga-sot-sa-otttttt");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<BlogBriefDto> getBlogBrief() {
        try {
            return blogRepository.findFirst3ByStatusNameOrderByCreateAtDesc(EStatus.SHOW).stream(
            ).map(
                    BlogBriefMapper.INSTANCE::blogBriefToDto
            ).toList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<BlogSummaryDto> getBlogSummary() {
        try {
            List<BlogSummaryProjection> blogSummaryDtoss = blogRepository.findFirst5ByStatusNameOrderByCreateAtDesc(EStatus.SHOW);

            List<BlogSummaryDto> blogSummaryDtos = blogRepository.findFirst5ByStatusNameOrderByCreateAtDesc(EStatus.SHOW).stream(
            ).map(
                    BlogSummaryMapper.INSTANCE::blogSummaryDto
            ).toList();


            return blogSummaryDtos;
        } catch (Exception e) {
            System.out.println(e.getMessage() + "at getBlogSummary()");
        }
        return null;
    }
}

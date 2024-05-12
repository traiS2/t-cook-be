package com.trainh.tcookbe.serviceImpl;

import com.trainh.tcookbe.mapper.blog.BriefBlogMapper;
import com.trainh.tcookbe.mapper.blog.DetailBlogMapper;
import com.trainh.tcookbe.mapper.blog.SummaryBlogMapper;
import com.trainh.tcookbe.model.dto.blog.BriefBlogDTO;
import com.trainh.tcookbe.model.dto.blog.DetailBlogDTO;
import com.trainh.tcookbe.model.dto.blog.SummaryBlogDTO;
import com.trainh.tcookbe.model.entity.*;
import com.trainh.tcookbe.model.enums.EStatus;
import com.trainh.tcookbe.model.projection.blog.DetailBlogProjection;
import com.trainh.tcookbe.model.projection.blog.SummaryBlogProjection;
import com.trainh.tcookbe.payload.request.blog.BlogCreationRequest;
import com.trainh.tcookbe.payload.request.introduction.IntroductionBlogCreationRequest;
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
    public String createBlog(BlogCreationRequest request) {
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
                for (IntroductionBlogCreationRequest introduction : request.getIntroduction()) {
                    introductions.add(new Introduction(introduction.getId(), introduction.getContent()));
                }

                Set<Ingredient> ingredients = new HashSet<>();
                request.getIngredient().forEach(ingredient -> ingredients.add(new Ingredient(ingredient.getId(), ingredient.getName())));

                Set<Recipe> recipes = new HashSet<>();
                final int[] posRecipe = {1};
                request.getRecipe().forEach(
                        recipe -> {
                            HashSet<DetailRecipe> detailsRecipes = new HashSet<>();
                            recipe.getDetailRecipe().forEach(
                                    detailRecipe -> {
                                        detailsRecipes.add(new DetailRecipe(detailRecipe.getId(), detailRecipe.getContent()));
                                    }
                            );
                            recipes.add(new Recipe(recipe.getId(), recipe.getName(), posRecipe[0], recipe.getImage(), detailsRecipes));
                            posRecipe[0]++;

                        }
                );

                Set<Category> categories = new HashSet<>();
                request.getCategory().forEach(category -> categories.add(new Category(category)));

                Set<Tag> tags = new HashSet<>();
                request.getTag().forEach(tag -> {
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
                    recipe.getDetailRecipe().forEach(detailsRecipe ->
                            detailsRecipe.setRecipe(recipe)
                    );
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
            return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<BriefBlogDTO> getBriefBlog() {
        try {
            return blogRepository.findFirst3ByStatusNameOrderByCreateAtDesc(EStatus.SHOW).stream(
            ).map(
                    BriefBlogMapper.INSTANCE::briefBlogToDto
            ).toList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<SummaryBlogDTO> getSummaryBlog() {
        try {
            List<SummaryBlogProjection> summaryBlogProjection = blogRepository.findFirst5ByStatusNameOrderByCreateAtDesc(EStatus.SHOW);

            return blogRepository.findFirst5ByStatusNameOrderByCreateAtDesc(EStatus.SHOW).stream(
            ).map(
                    SummaryBlogMapper.INSTANCE::blogSummaryDto
            ).toList();

        } catch (Exception e) {
            System.out.println(e.getMessage() + "at getBlogSummary()");
        }
        return null;
    }

    @Override
    public Optional<DetailBlogDTO> getDetailBlog(String link) {
        try {
            Optional<DetailBlogProjection> detailBlogProjection;
       detailBlogProjection = blogRepository.findBlogByLink(link);
            System.out.println(detailBlogProjection.get().getRecipe().get(0).getDetailRecipe().get(0).getContent());
            return detailBlogProjection.map(DetailBlogMapper.INSTANCE::detailBlogToDTO);
        } catch (Exception e) {
            System.out.println(e.getMessage() + "at getDetailBlog()");
        }
        return Optional.empty();
    }
}

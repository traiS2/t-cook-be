package com.trainh.tcookbe.serviceImpl;

import com.trainh.tcookbe.model.entity.Blog;
import com.trainh.tcookbe.model.entity.Recipe;
import com.trainh.tcookbe.payload.request.recipe.CreateRecipeRequest;
import com.trainh.tcookbe.repository.BlogRepository;
import com.trainh.tcookbe.repository.RecipeRepository;
import com.trainh.tcookbe.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;
    private final BlogRepository blogRepository;

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository, BlogRepository blogRepository) {
        this.recipeRepository = recipeRepository;
        this.blogRepository = blogRepository;
    }

    @Override
    public String createRecipe(CreateRecipeRequest request) {
        try {
            Optional<Blog> blogOptional = blogRepository.findById(request.getBlogId());
            if (blogOptional.isPresent()) {
                Recipe recipe = new Recipe(request.getTitle().trim(),request.getStep(), request.getDescription().trim(), request.getImage(), blogOptional.get());
                recipeRepository.save(recipe);
            } else {
                return "Create recipe failed";
            }
        } catch (Exception e) {
            return "Create recipe failed";
        }
        return null;
    }
}

package com.trainh.tcookbe.mapper.blog;

import com.trainh.tcookbe.model.dto.blog.DetailBlogDTO;
import com.trainh.tcookbe.model.dto.ingredient.IngredientDTO;
import com.trainh.tcookbe.model.dto.introduction.IntroductionDTO;
import com.trainh.tcookbe.model.dto.recipe.DetailRecipeDTO;
import com.trainh.tcookbe.model.dto.recipe.RecipeDTO;
import com.trainh.tcookbe.model.projection.blog.DetailBlogProjection;
import com.trainh.tcookbe.model.projection.ingredient.IngredientProjection;
import com.trainh.tcookbe.model.projection.introduction.IntroductionProjection;
import com.trainh.tcookbe.model.projection.recipe.RecipeProjection;
import com.trainh.tcookbe.model.projection.user.UserFullNameProjection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface DetailBlogMapper {
    DetailBlogMapper INSTANCE = Mappers.getMapper(DetailBlogMapper.class);

    @Mapping(source = "user.fullName", target = "user")
    DetailBlogDTO detailBlogToDTO(DetailBlogProjection detailBlogProjection);

    @Mapping(source = "ingredient", target = "ingredient", qualifiedByName = "mapIngredient")
    @Named("mapIngredient")
    default List<IngredientDTO> mapIngredient(List<IngredientProjection> ingredient) {
        return ingredient.stream()
                         .map(ingredientProjection -> new IngredientDTO(ingredientProjection.getId(), ingredientProjection.getName()))
                         .collect(Collectors.toList());
    }

    @Mapping(source = "recipe", target = "recipe", qualifiedByName = "mapRecipe")
    @Named("mapRecipe")
    default List<RecipeDTO> mapRecipe(List<RecipeProjection> recipe) {

        return recipe.stream()
                     .map(recipeProjection -> new RecipeDTO(recipeProjection.getId(), recipeProjection.getName(),
                             recipeProjection.getImage(),
                             (List<DetailRecipeDTO>) recipeProjection.getDetailRecipe().stream().map(
                                        detailRecipeProjection -> new DetailRecipeDTO(detailRecipeProjection.getId(),
                                                detailRecipeProjection.getContent()
                                )).toList())).toList();
    }

    @Mapping(source = "introduction", target = "introduction", qualifiedByName = "mapIntroduction")
    @Named("mapIntroduction")
    default List<IntroductionDTO> mapIntroduction(List<IntroductionProjection> introduction) {
        return introduction.stream()
                           .map(introductionProjection -> new IntroductionDTO(introductionProjection.getId(), introductionProjection.getContent()))
                           .collect(Collectors.toList());
    }

    @Named("mapUser")
    default String mapUser(UserFullNameProjection user) {
        return user.getFullName();
    }
}

package com.trainh.tcookbe.mapper.recipe;

import com.trainh.tcookbe.model.dto.recipe.DetailRecipeDTO;
import com.trainh.tcookbe.model.dto.recipe.RecipeDTO;
import com.trainh.tcookbe.model.projection.recipe.DetailRecipeProjection;
import com.trainh.tcookbe.model.projection.recipe.RecipeProjection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface RecipeMapper {

    RecipeMapper INSTANCE = Mappers.getMapper(RecipeMapper.class);

    RecipeDTO recipeToDTO(RecipeProjection recipeProjection);

    @Mapping(source = "detailRecipe", target = "detailRecipeDTO", qualifiedByName = "mapDetailRecipe")
    @Named("mapDetailRecipe")
    default List<DetailRecipeDTO> mapDetailRecipe(List<DetailRecipeProjection> detailRecipe) {
        return detailRecipe.stream()
                           .map(detailRecipeProjection -> new DetailRecipeDTO(detailRecipeProjection.getId(), detailRecipeProjection.getContent()))
                           .collect(Collectors.toList());
    }
}

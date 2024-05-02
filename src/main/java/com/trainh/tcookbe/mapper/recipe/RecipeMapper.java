package com.trainh.tcookbe.mapper.recipe;

import com.trainh.tcookbe.model.dto.recipe.RecipeDto;
import com.trainh.tcookbe.model.projection.recipe.RecipeProjection;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RecipeMapper {

    RecipeMapper INSTANCE = Mappers.getMapper(RecipeMapper.class);

    RecipeDto recipeToDto(RecipeProjection recipeProjection);
}

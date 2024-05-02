package com.trainh.tcookbe.mapper.recipe;

import com.trainh.tcookbe.model.dto.recipe.RecipeDetailsDto;
import com.trainh.tcookbe.model.projection.recipe.RecipeDetailsProjection;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RecipeDetailsMapper {

    RecipeDetailsMapper INSTANCE = Mappers.getMapper(RecipeDetailsMapper.class);

    RecipeDetailsDto recipeToDto(RecipeDetailsProjection recipeDetailsProjection);
}

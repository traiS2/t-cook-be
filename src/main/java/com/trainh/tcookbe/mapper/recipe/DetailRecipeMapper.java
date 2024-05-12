package com.trainh.tcookbe.mapper.recipe;

import com.trainh.tcookbe.model.dto.recipe.DetailRecipeDTO;
import com.trainh.tcookbe.model.projection.recipe.DetailRecipeProjection;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DetailRecipeMapper {

    DetailRecipeMapper INSTANCE = Mappers.getMapper(DetailRecipeMapper.class);

    DetailRecipeDTO recipeToDto(DetailRecipeProjection detailRecipeProjection);
}

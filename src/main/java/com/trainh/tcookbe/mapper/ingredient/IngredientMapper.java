package com.trainh.tcookbe.mapper.ingredient;

import com.trainh.tcookbe.model.dto.ingredient.IngredientDTO;
import com.trainh.tcookbe.model.entity.Ingredient;
import com.trainh.tcookbe.model.projection.ingredient.IngredientProjection;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IngredientMapper {
    IngredientMapper INSTANCE = Mappers.getMapper(IngredientMapper.class);
    IngredientDTO ingredientToDTO(IngredientProjection ingredientProjection);
}

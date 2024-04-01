package com.trainh.tcookbe.mapper;

import com.trainh.tcookbe.dto.category.CategoryDto;
import com.trainh.tcookbe.model.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDto categoryToDto(Category category);
}

package com.trainh.tcookbe.mapper;

import com.trainh.tcookbe.model.dto.category.CategoryDto;
import com.trainh.tcookbe.model.projection.category.CategoryBriefProjection;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryBriefMapper {
    CategoryBriefMapper INSTANCE = Mappers.getMapper(CategoryBriefMapper.class);

    CategoryDto categoryToDto(CategoryBriefProjection categoryBriefProjection);
}

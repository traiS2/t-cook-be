package com.trainh.tcookbe.mapper;

import com.trainh.tcookbe.model.dto.category.CategoryDTO;
import com.trainh.tcookbe.model.projection.category.CategoryBriefProjection;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BriefCategoryMapper {
    BriefCategoryMapper INSTANCE = Mappers.getMapper(BriefCategoryMapper.class);

    CategoryDTO categoryToDto(CategoryBriefProjection categoryBriefProjection);
}

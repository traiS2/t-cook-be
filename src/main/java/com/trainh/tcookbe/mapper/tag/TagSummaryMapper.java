package com.trainh.tcookbe.mapper.tag;

import com.trainh.tcookbe.model.dto.tag.TagSummaryDto;
import com.trainh.tcookbe.model.entity.Tag;
import com.trainh.tcookbe.model.projection.tag.TagSummaryProjection;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TagSummaryMapper {
    TagSummaryMapper INSTANCE = Mappers.getMapper(TagSummaryMapper.class);
    TagSummaryDto tagSummaryToDto(Tag tag);
}

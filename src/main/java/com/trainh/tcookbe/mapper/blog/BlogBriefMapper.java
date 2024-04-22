package com.trainh.tcookbe.mapper.blog;

import com.trainh.tcookbe.model.dto.blog.BlogBriefDto;
import com.trainh.tcookbe.model.projection.blog.BlogBriefProjection;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BlogBriefMapper {
    BlogBriefMapper INSTANCE = Mappers.getMapper(BlogBriefMapper.class);

    BlogBriefDto blogBriefToDto(BlogBriefProjection blogBriefProjection);
}

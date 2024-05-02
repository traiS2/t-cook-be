package com.trainh.tcookbe.mapper.blog;

import com.trainh.tcookbe.model.dto.blog.BriefBlogDTO;
import com.trainh.tcookbe.model.projection.blog.BriefBlogProjection;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BriefBlogMapper {
    BriefBlogMapper INSTANCE = Mappers.getMapper(BriefBlogMapper.class);

    BriefBlogDTO briefBlogToDto(BriefBlogProjection briefBlogProjection);
}

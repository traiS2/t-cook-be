package com.trainh.tcookbe.mapper;

import com.trainh.tcookbe.model.dto.tag.TagDTO;
import com.trainh.tcookbe.model.entity.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TagMapper {
    TagMapper INSTANCE = Mappers.getMapper(TagMapper.class);

    TagDTO tagToDto(Tag tag);
}

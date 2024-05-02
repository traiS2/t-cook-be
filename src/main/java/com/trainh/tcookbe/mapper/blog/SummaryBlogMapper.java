package com.trainh.tcookbe.mapper.blog;

import com.trainh.tcookbe.model.dto.blog.SummaryBlogDTO;
import com.trainh.tcookbe.model.dto.introduction.IntroductionDTO;
import com.trainh.tcookbe.model.dto.tag.TagDTO;
import com.trainh.tcookbe.model.projection.blog.SummaryBlogProjection;
import com.trainh.tcookbe.model.projection.introduction.IntroductionProjection;
import com.trainh.tcookbe.model.projection.tag.TagNameProjection;
import com.trainh.tcookbe.model.projection.user.UserFullNameProjection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface SummaryBlogMapper {
    SummaryBlogMapper INSTANCE = Mappers.getMapper(SummaryBlogMapper.class);

    @Mapping(source = "introduction", target = "introduction", qualifiedByName = "mapIntroduction")
    @Mapping(source = "tags", target = "tags", qualifiedByName = "mapTags")
    @Mapping(source = "user.fullName", target = "user")
    SummaryBlogDTO blogSummaryDto(SummaryBlogProjection summaryBlogProjection);


    @Named("mapIntroduction")
    default List<IntroductionDTO> mapIntroduction(List<IntroductionProjection> introduction) {
        return introduction.stream()
                .map(introductionProjection -> new IntroductionDTO(introductionProjection.getId(), introductionProjection.getContent()))
                .collect(Collectors.toList());
    }

    @Named("mapTags")
    default List<TagDTO> mapTags(List<TagNameProjection> tags) {
        return tags.stream()
                .map(tagNameProjection -> new TagDTO(tagNameProjection.getId(), tagNameProjection.getName()))
                .collect(Collectors.toList());
    }

    @Named("mapUser")
    default String mapUser(UserFullNameProjection user) {
        return user.getFullName();
    }
}

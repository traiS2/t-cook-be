package com.trainh.tcookbe.mapper.blog;

import com.trainh.tcookbe.model.dto.blog.BlogSummaryDto;
import com.trainh.tcookbe.model.dto.tag.TagNameProjection;
import com.trainh.tcookbe.model.projection.blog.BlogSummaryProjection;
import com.trainh.tcookbe.model.projection.introduction.IntroductionContentProjection;
import com.trainh.tcookbe.model.projection.user.UserFullNameProjection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface BlogSummaryMapper {
    BlogSummaryMapper INSTANCE = Mappers.getMapper(BlogSummaryMapper.class);

    @Mapping(source = "introduction", target = "introduction", qualifiedByName = "mapIntroduction")
    @Mapping(source = "tags", target = "tags", qualifiedByName = "mapTags")
    @Mapping(source = "user.fullName", target = "user")
    BlogSummaryDto blogSummaryDto(BlogSummaryProjection blogSummaryProjection);


    @Named("mapIntroduction")
    default List<String> mapIntroduction(List<IntroductionContentProjection> introduction) {
        return introduction.stream()
                .map(IntroductionContentProjection::getContent)
                .collect(Collectors.toList());
    }

    @Named("mapTags")
    default List<String> mapTags(List<TagNameProjection> tags) {
        return tags.stream()
                .map(TagNameProjection::getName)
                .collect(Collectors.toList());
    }

    @Named("mapUser")
    default String mapUser(UserFullNameProjection user) {
        return user.getFullName();
    }
}

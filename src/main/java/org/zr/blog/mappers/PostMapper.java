package org.zr.blog.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.zr.blog.domain.CreatePostRequest;
import org.zr.blog.domain.UpdatePostRequest;
import org.zr.blog.domain.dtos.CreatePostRequestDto;
import org.zr.blog.domain.dtos.PostDto;
import org.zr.blog.domain.dtos.UpdatePostRequestDto;
import org.zr.blog.domain.entities.Post;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {
    @Mapping(target = "author", source = "author")
    @Mapping(target = "category", source = "category")
    @Mapping(target = "tags", source = "tags")
    PostDto toDto(Post post);

    CreatePostRequest toCreatePostRequest(CreatePostRequestDto dto);

    UpdatePostRequest toUpdatePostRequest(UpdatePostRequestDto dto);
}

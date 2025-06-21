package org.zr.blog.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.zr.blog.domain.PostStatus;
import org.zr.blog.domain.dtos.CategoryDto;
import org.zr.blog.domain.dtos.CreateCategoryRequest;
import org.zr.blog.domain.entities.Category;
import org.zr.blog.domain.entities.Post;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {
    @Mapping(target = "postCount", source = "posts", qualifiedByName = "calculatePostCount")
    CategoryDto toDto(Category category);

    Category toEntity(CreateCategoryRequest createCategoryRequest);

    @Named("calculatePostCount")
    default Long calculatePostCount(List<Post> posts) {
        if (posts == null || posts.isEmpty()) {
            return 0L;
        }
        return posts.stream().filter(post -> PostStatus.PUBLISHED.equals(post.getStatus()))
                .count();
    }
}

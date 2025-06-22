package org.zr.blog.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zr.blog.domain.dtos.CreateTagsRequest;
import org.zr.blog.domain.dtos.TagDto;
import org.zr.blog.domain.entities.Tag;
import org.zr.blog.mappers.TagMapper;
import org.zr.blog.services.TagService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;
    private final TagMapper tagMapper;

    @GetMapping
    public ResponseEntity<List<TagDto>> getAllTags() {
        List<Tag> tags = tagService.getTags();
        List<TagDto> tagDtoList = tags.stream().map(tagMapper::toTagDto).toList();
        return ResponseEntity.ok(tagDtoList);
    }

    @PostMapping
    public ResponseEntity<List<TagDto>> createTags(@RequestBody @Valid CreateTagsRequest createTagsRequest) {
        List<Tag> savedTags = tagService.createTags(createTagsRequest.getNames());
        List<TagDto> createdTagtagDtoList = savedTags.stream().map(tagMapper::toTagDto).toList();
        return new ResponseEntity<>(createdTagtagDtoList,HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteTags(@PathVariable UUID id) {
        tagService.deleteTag(id);
        return ResponseEntity.noContent().build();
    }
}

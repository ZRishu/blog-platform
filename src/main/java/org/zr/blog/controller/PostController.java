package org.zr.blog.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zr.blog.domain.CreatePostRequest;
import org.zr.blog.domain.UpdatePostRequest;
import org.zr.blog.domain.dtos.CreatePostRequestDto;
import org.zr.blog.domain.dtos.PostDto;
import org.zr.blog.domain.dtos.UpdatePostRequestDto;
import org.zr.blog.domain.entities.Post;
import org.zr.blog.domain.entities.User;
import org.zr.blog.mappers.PostMapper;
import org.zr.blog.services.PostService;
import org.zr.blog.services.UserService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostMapper postMapper;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts(
            @RequestParam(required = false) UUID categoryId,
            @RequestParam(required = false) UUID tagId) {
        List<Post> posts = postService.getAllPosts(categoryId, tagId);
        List<PostDto> postDtos = posts.stream().map(postMapper::toDto).toList();
        return ResponseEntity.ok(postDtos);
    }

    @GetMapping(path = "/drafts")
    public ResponseEntity<List<PostDto>> getDrafts(@RequestAttribute UUID userId) {
        User loggedInUser = userService.getUserById(userId);
        List<Post> draftPosts = postService.getDraftPosts(loggedInUser);
        List<PostDto> postDtos = draftPosts.stream().map(postMapper::toDto).toList();
        return ResponseEntity.ok(postDtos);
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(
            @Valid @RequestBody CreatePostRequestDto postDto,
            @RequestAttribute UUID userId
            ) {
        User loggedInUser = userService.getUserById(userId);
        CreatePostRequest createPostRequest = postMapper.toCreatePostRequest(postDto);
        Post createdPost = postService.createPost(loggedInUser, createPostRequest);
        return new ResponseEntity<>(postMapper.toDto(createdPost), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<PostDto> updatePost(
            @PathVariable UUID postId,
            @Valid @RequestBody UpdatePostRequestDto dto) {
        UpdatePostRequest updatePostRequest = postMapper.toUpdatePostRequest(dto);
        Post post = postService.updatePost(postId, updatePostRequest);
        return ResponseEntity.ok(postMapper.toDto(post));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PostDto> getPost(@PathVariable UUID postId) {
        Post post = postService.getPost(postId);
        return ResponseEntity.ok(postMapper.toDto(post));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable UUID id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}

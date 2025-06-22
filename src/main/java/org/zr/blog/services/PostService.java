package org.zr.blog.services;

import org.zr.blog.domain.CreatePostRequest;
import org.zr.blog.domain.UpdatePostRequest;
import org.zr.blog.domain.entities.Post;
import org.zr.blog.domain.entities.User;

import java.util.List;
import java.util.UUID;

public interface PostService {
    List<Post> getAllPosts(UUID categoryId, UUID tagId);
    List<Post> getDraftPosts(User user);
    Post createPost(User user, CreatePostRequest  createPostRequest);
    Post updatePost(UUID id, UpdatePostRequest updatePostRequest);
    Post getPost(UUID id);
    void deletePost(UUID id);
}

package com.springboot.blog.service;

import com.springboot.blog.dto.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);
    List<PostDto> getAllPosts();
    PostDto getPostById(Long postId);
    PostDto updatePost( PostDto postDto, Long postId);
    void deletePostById(Long postId);


}

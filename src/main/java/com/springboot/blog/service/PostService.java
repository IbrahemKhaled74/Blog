package com.springboot.blog.service;

import com.springboot.blog.dto.PostDto;
import com.springboot.blog.dto.PostPagination;


public interface PostService {
    PostDto createPost(PostDto postDto);
    PostPagination getAllPosts(int pageNo, int pageSize,String sortBy,String sortDir);
    PostDto getPostById(Long postId);
    PostDto updatePost( PostDto postDto, Long postId);
    void deletePostById(Long postId);


}

package com.springboot.blog.service.impl;

import com.springboot.blog.dto.PostDto;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.model.Post;
import com.springboot.blog.repository.PostRepo;
import com.springboot.blog.service.PostService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepo postRepo;

    public PostServiceImpl(PostRepo postRepo) {
        this.postRepo = postRepo;
    }

    @Override
    public PostDto createPost(PostDto postDto){
        Post post = mapToPost(postDto);
        Post savedPost = postRepo.save(post);
        return mapToPostDto(savedPost);
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> savedPosts = postRepo.findAll();
       return savedPosts.stream().map(this::mapToPostDto).collect(Collectors.toList());
    }

    @Override
    public PostDto getPostById(Long postId) {
        Post post = postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","id",postId));
        return mapToPostDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Long postId) {
        Post post = postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","id",postId));
        Post postResponse = mapToPost(postDto);
        post.setContent(postResponse.getContent());
        post.setDescription(postResponse.getDescription());
        post.setTitle(postResponse.getTitle());
        Post savedPost = postRepo.save(post);
        return mapToPostDto(savedPost) ;
    }

    @Override
    public void deletePostById(Long postId) {
        postRepo.deleteById(postId);
    }

    private PostDto mapToPostDto(Post post){
       return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .description(post.getDescription())
                .content(post.getContent()).build();
    }
    private Post mapToPost(PostDto postDto){
       return Post.builder()
                .id(postDto.getId())
                .title(postDto.getTitle())
                .description(postDto.getDescription())
                .content(postDto.getContent()).build();
    }

}

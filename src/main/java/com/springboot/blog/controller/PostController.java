package com.springboot.blog.controller;

import com.springboot.blog.dto.PostDto;
import com.springboot.blog.service.PostService;
import com.springboot.blog.service.impl.PostServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("blog/post")
public class PostController {
    private final PostService postService;

    public PostController(PostServiceImpl postService) {
        this.postService = postService;
    }
    @PostMapping
    ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }
    @GetMapping
    ResponseEntity<List<PostDto>> getAllPosts(){
        return new ResponseEntity<>(postService.getAllPosts(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") Long postId){
        return ResponseEntity.ok(postService.getPostById(postId));
    }
    @PutMapping("/{id}")
    ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable(name = "id") Long postId){
        return ResponseEntity.ok(postService.updatePost(postDto, postId));
    }
    @DeleteMapping("/{id}")
    ResponseEntity deletePost(@PathVariable(name = "id") Long postId){
        postService.deletePostById( postId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}

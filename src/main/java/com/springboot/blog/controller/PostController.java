package com.springboot.blog.controller;

import com.springboot.blog.dto.PostDto;
import com.springboot.blog.dto.PostPagination;
import com.springboot.blog.service.PostService;
import com.springboot.blog.service.impl.PostServiceImpl;
import com.springboot.blog.utils.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
    ResponseEntity<PostPagination> getAllPosts(
            @RequestParam(name = "pageNo" , defaultValue = AppConstants.PAGE_NUMBER,required = false) int pageNo,
            @RequestParam(name = "pageSize" ,defaultValue = AppConstants.PAGE_SIZE ,required = false) int pageSize,
            @RequestParam(name = "sortBy" ,defaultValue = AppConstants.SORT_BY ,required = false) String sortBy,
            @RequestParam(name = "sortDir" ,defaultValue = AppConstants.SORT_DIRECTION ,required = false) String sortDir
    ){
        return new ResponseEntity<>(postService.getAllPosts(pageNo,pageSize,sortBy,sortDir), HttpStatus.OK);
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

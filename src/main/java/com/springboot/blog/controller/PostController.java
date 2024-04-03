package com.springboot.blog.controller;

import com.springboot.blog.dto.PostDto;
import com.springboot.blog.dto.PostPagination;
import com.springboot.blog.service.PostService;
import com.springboot.blog.service.impl.PostServiceImpl;
import com.springboot.blog.utils.AppConstants;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("blog/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostServiceImpl postService) {
        this.postService = postService;
    }
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
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
    ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") long postId){
        return ResponseEntity.ok(postService.getPostById(postId));
    }
    @GetMapping("/category/{id}")
    ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable(name = "id") long categoryId){
        return ResponseEntity.ok(postService.getPostsByCategory(categoryId));
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto,
                                       @PathVariable(name = "id") long postId){
        return ResponseEntity.ok(postService.updatePost(postDto, postId));
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<String> deletePost(@PathVariable(name = "id") Long postId){
        postService.deletePostById( postId);
        return new ResponseEntity<>("Post Deleted Successfully",HttpStatus.OK);
    }


}

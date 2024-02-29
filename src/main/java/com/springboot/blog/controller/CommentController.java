package com.springboot.blog.controller;

import com.springboot.blog.dto.CommentDto;
import com.springboot.blog.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blog/posts")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    @PostMapping("/{postId}/comments")
    ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") long postId,
                                             @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.createComment(postId,commentDto), HttpStatus.CREATED);
    }
    @GetMapping("/{postId}/comments/{commentId}")
    ResponseEntity<CommentDto> getCommentById(@PathVariable(value = "postId") long postId,
                                              @PathVariable(value = "commentId") long commentId){
        return ResponseEntity.ok(commentService.getCommentById(postId,commentId));
    }
 @GetMapping("/{postId}/comments")
    ResponseEntity<List<CommentDto>> getAllComment(@PathVariable(value = "postId") long postId){
        return ResponseEntity.ok(commentService.getAllCommentById(postId));
    }
    @PutMapping("/{postId}/comments/{commentId}")
    ResponseEntity<CommentDto> updateCommentById(@PathVariable(value = "postId") long postId,
                                                 @PathVariable(value = "commentId") long commentId,
                                                 @RequestBody CommentDto commentDto){
        return ResponseEntity.ok(commentService.updateComment(postId,commentId,commentDto));
    }
    @DeleteMapping("/{postId}/comments/{commentId}")
    ResponseEntity<String> deleteCommentById(@PathVariable(value = "postId") long postId,
                                     @PathVariable(value = "commentId") long commentId){
        commentService.deleteComment(postId,commentId);
        return new ResponseEntity<>("Comment Deleted Successfully",HttpStatus.OK);
    }
}

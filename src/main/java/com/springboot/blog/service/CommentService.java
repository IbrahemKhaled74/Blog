package com.springboot.blog.service;

import com.springboot.blog.dto.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long postId,CommentDto commentDto);
    CommentDto getCommentById(long postId,long commentId);
    List<CommentDto> getAllCommentById(long postId);
    CommentDto updateComment(long postId,long commentId,CommentDto commentDto);
    void deleteComment(long postId,long commentId);


}

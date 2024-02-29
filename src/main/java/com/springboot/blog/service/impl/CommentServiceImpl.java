package com.springboot.blog.service.impl;

import com.springboot.blog.dto.CommentDto;
import com.springboot.blog.dto.PostDto;
import com.springboot.blog.exception.BlogException;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.model.Comment;
import com.springboot.blog.model.Post;
import com.springboot.blog.repository.CommentRepo;
import com.springboot.blog.repository.PostRepo;
import com.springboot.blog.service.CommentService;
import org.hibernate.annotations.NotFound;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepo commentRepo;
    private final PostRepo postRepo;

    public CommentServiceImpl(CommentRepo commentRepo, PostRepo postRepo) {
        this.commentRepo = commentRepo;
        this.postRepo = postRepo;
    }


    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Comment comment =mapToEntity(commentDto);
        Post post = postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","id",postId));
        comment.setPost(post);
        Comment commentResponse = commentRepo.save(comment);
        return mapToDto(commentResponse);
    }

    @Override
    public CommentDto getCommentById(long postId,long commentId) {
        Comment comment = checkCommentRelatedToPost(postId, commentId);
        return mapToDto(comment);
    }

    @Override
    public List<CommentDto> getAllCommentById(long postId) {
        return commentRepo.findByPostId(postId).stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public CommentDto updateComment(long postId, long commentId,CommentDto commentDto) {
        Comment comment = checkCommentRelatedToPost(postId, commentId);
        comment.setBody(commentDto.getBody());
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        Comment updatedComment = commentRepo.save(comment);
        return mapToDto(updatedComment);
    }

    @Override
    public void deleteComment(long postId, long commentId) {
        Comment comment = checkCommentRelatedToPost(postId, commentId);
        commentRepo.delete(comment);
    }
    private Comment checkCommentRelatedToPost(long postId,long commentId){
        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        Comment comment = commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
        if (!comment.getPost().getId().equals(post.getId())){
            throw new BlogException(HttpStatus.BAD_REQUEST,"No Comment related to this post");
        }
        return comment;

    }
    private CommentDto mapToDto(Comment comment){
        return CommentDto.builder()
                .id(comment.getId())
                .email(comment.getEmail())
                .body(comment.getBody())
                .name(comment.getName())
                .build();
    }
    private Comment mapToEntity(CommentDto commentDto){
        return Comment.builder()
                .id(commentDto.getId())
                .email(commentDto.getEmail())
                .body(commentDto.getBody())
                .name(commentDto.getName())
                .build();
    }


}

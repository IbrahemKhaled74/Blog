package com.springboot.blog.dto;

import com.springboot.blog.model.Post;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentDto {
    private Long id;
    private String name;
    private String email;
    private String body;

}

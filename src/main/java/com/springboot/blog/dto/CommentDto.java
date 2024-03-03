package com.springboot.blog.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDto {
    private long id;
    @NotEmpty
    @Size(min = 2,message = "Name must have at least 2 character")
    private String name;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    private String body;

}

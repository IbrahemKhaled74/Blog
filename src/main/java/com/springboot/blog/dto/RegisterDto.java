package com.springboot.blog.dto;

import com.springboot.blog.utils.RoleName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class RegisterDto {
    private String name;
    private String username;
    private String password;
    private String email;
}

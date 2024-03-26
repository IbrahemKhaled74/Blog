package com.springboot.blog.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JWTResponse {
    private String token;
    private String type="Bearer";
}

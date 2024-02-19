package com.springboot.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostPagination {
    private List<PostDto>content;
    private int pageNumber;
    private int pageSize;
    private int totalElements;
    private int totalPage;
    private boolean isLast;
}

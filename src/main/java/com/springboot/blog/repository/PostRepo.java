package com.springboot.blog.repository;

import com.springboot.blog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostRepo extends JpaRepository<Post, Long> {
}

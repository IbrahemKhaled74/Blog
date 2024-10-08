package com.springboot.blog.repository;

import com.springboot.blog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface PostRepo extends JpaRepository<Post, Long> {
    List<Post> findByCategoryId(long categoryId);
}

package com.springboot.blog.repository;



import com.springboot.blog.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserDetails,Long> {
    Optional<UserDetails>findByEmail(String email);
    Optional<UserDetails> findByUsername(String username);
    Optional<UserDetails> findByEmailOrUsername(String email, String username);
    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);
}

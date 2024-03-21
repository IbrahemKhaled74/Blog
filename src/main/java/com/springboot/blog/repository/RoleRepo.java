package com.springboot.blog.repository;

import com.springboot.blog.model.Role;
import com.springboot.blog.utils.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role,Long> {
    Optional<Role>findByRole(RoleName roleName);
}

package com.springboot.blog.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL ,orphanRemoval = true)
    Set<Post>posts;
}

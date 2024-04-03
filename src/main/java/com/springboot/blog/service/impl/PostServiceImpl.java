package com.springboot.blog.service.impl;

import com.springboot.blog.dto.PostDto;
import com.springboot.blog.dto.PostPagination;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.model.Category;
import com.springboot.blog.model.Post;
import com.springboot.blog.repository.CategoryRepo;
import com.springboot.blog.repository.PostRepo;
import com.springboot.blog.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepo postRepo;
    private final CategoryRepo categoryRepo;
    private final ModelMapper modelMapper;

    public PostServiceImpl(PostRepo postRepo, CategoryRepo categoryRepo, ModelMapper modelMapper) {
        this.postRepo = postRepo;
        this.categoryRepo = categoryRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public PostDto createPost(PostDto postDto){
        Post post = mapToPost(postDto);
        Category category = categoryRepo.findById(postDto.getCategoryId()).orElseThrow(() ->
                new ResourceNotFoundException("Category", "id", postDto.getCategoryId()));
        post.setCategory(category);
        Post savedPost = postRepo.save(post);
        return mapToPostDto(savedPost);
    }

    @Override
    public PostPagination getAllPosts(int pageNo, int pageSize,String sortBy ,String sortDir) {
        Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo,pageSize,sort);

        Page<Post> posts = postRepo.findAll(pageable);
        List<Post> content = posts.getContent();
        List<PostDto> savedPosts = content.stream().map(this::mapToPostDto).collect(Collectors.toList());
        PostPagination postPagination = PostPagination.builder().
                content(savedPosts)
                .pageNumber(pageNo)
                .pageSize(pageSize)
                .totalPage(posts.getTotalPages())
                .totalElements(posts.getNumberOfElements())
                .isLast(posts.isLast())
                .build();
        return postPagination;
    }

    @Override
    public PostDto getPostById(Long postId) {
        Post post = postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","id",postId));
        return mapToPostDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Long postId) {
        Post post = postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","id",postId));
        Category category = categoryRepo.findById(postDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Category", "id", postDto.getId()));
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());
        post.setTitle(postDto.getTitle());
        post.setCategory(category);
        Post savedPost = postRepo.save(post);
        return mapToPostDto(savedPost) ;
    }

    @Override
    public void deletePostById(Long postId) {
        postRepo.deleteById(postId);
    }

    @Override
    public List<PostDto> getPostsByCategory(long categoryId) {
        return postRepo.findByCategoryId(categoryId).stream().map(this::mapToPostDto).collect(Collectors.toList());
    }

    private PostDto mapToPostDto(Post post){
       return modelMapper.map(post,PostDto.class);
    }
    private Post mapToPost(PostDto postDto){
       return modelMapper.map(postDto,Post.class);
    }

}

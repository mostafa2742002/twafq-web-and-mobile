package com.nasr.twafq.blog.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.nasr.twafq.blog.dto.BlogDTO;
import com.nasr.twafq.blog.dto.BlogMapper;
import com.nasr.twafq.blog.entity.Blog;
import com.nasr.twafq.blog.entity.PageResponse;
import com.nasr.twafq.blog.repo.BlogRepository;
import com.nasr.twafq.exception.ResourceNotFoundException;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;

    public void createBlog(@Valid BlogDTO blogDTO) {
        blogDTO.setId(null); // Ensure ID is null for creation
        Blog blog = BlogMapper.toBlog(blogDTO);

        Optional<Blog> blogOptional = blogRepository.findByTitle(blog.getTitle());
        if (blogOptional.isPresent()) {
            throw new IllegalStateException("Blog already exists");
        }

        blogRepository.save(blog);
    }

    public BlogDTO getBlog(@NotNull String blogId) {
        Blog blog = blogRepository.findById(blogId).orElseThrow(
                () -> new ResourceNotFoundException("Blog", "Blog Id", blogId));
        return BlogMapper.toBlogDto(blog);
    }

    public boolean updateBlog(@Valid BlogDTO blogDTO) {
        Blog blog = BlogMapper.toBlog(blogDTO);
        Optional<Blog> blogOptional = blogRepository.findById(blog.getId());
        if (blogOptional.isEmpty())
            throw new ResourceNotFoundException("Blog", "Blog Id", blogDTO.getId());

        blogRepository.save(blog);
        return true;
    }

    public boolean deleteBlog(@NotNull String blogId) {
        Blog blog = blogRepository.findById(blogId).orElseThrow(
                () -> new ResourceNotFoundException("Blog", "Blog Id", blogId));

        blogRepository.delete(blog);
        return true;
    }

    public PageResponse<Blog> findAllBlogs(int page, int size) {
        Page<Blog> blogPage = blogRepository.findAll(PageRequest.of(page, size));

        PageResponse<Blog> response = new PageResponse<>();
        response.setContent(blogPage.getContent());
        response.setNumber(blogPage.getNumber());
        response.setSize(blogPage.getSize());
        response.setTotalElements(blogPage.getTotalElements());
        response.setTotalPages(blogPage.getTotalPages());
        response.setFirst(blogPage.isFirst());
        response.setLast(blogPage.isLast());

        return response;
    }
}

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

    private BlogRepository blogRepository;

    public void createBlog(@Valid BlogDTO blogDTO) {
        blogDTO.setId(null);
        Blog blog = BlogMapper.toBlog(blogDTO);

        Optional<Blog> blogOptional = blogRepository.findByTitle(blog.getTitle());
        if (blogOptional.isPresent()) {
            throw new IllegalStateException("Blog already exists");
        }

        blogRepository.save(blog);
    }

    public BlogDTO getCourse(@NotNull String courseId) {
        Blog blog = blogRepository.findById(courseId).orElseThrow(
                () -> new ResourceNotFoundException("Blog", "Blog Id", courseId));
        BlogDTO blogDTO = BlogMapper.toBlogDto(blog);
        return blogDTO;
    }

    public boolean updateCourse(@Valid BlogDTO blogDTO) {
        boolean isUpdated = false;
        Blog blog = BlogMapper.toBlog(blogDTO);
        Optional<Blog> blogOptional = blogRepository.findById(blog.getId());
        if (blogOptional.isEmpty())
            throw new ResourceNotFoundException("Blog", "Blog Id", blogDTO.getId());

        blogRepository.save(blog);
        isUpdated = true;

        return isUpdated;
    }

    public boolean deleteCourse(@NotNull String blogId) {
        boolean isDeleted = false;
        Blog blog = blogRepository.findById(blogId).orElseThrow(
                () -> new ResourceNotFoundException("Blog", "Blog Id", blogId));

        blogRepository.delete(blog);

        isDeleted = true;
        return isDeleted;
    }

    public PageResponse<Blog> findAllCourses(int page, int size) {
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

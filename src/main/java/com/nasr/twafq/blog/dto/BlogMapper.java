package com.nasr.twafq.blog.dto;

import com.nasr.twafq.blog.entity.Blog;

import jakarta.validation.Valid;

public class BlogMapper {

    public static Blog toBlog(@Valid BlogDTO blogDTO) {
        if (blogDTO == null) {
            return null;
        }

        Blog blog = new Blog();
        blog.setId(blogDTO.getId());
        blog.setTitle(blogDTO.getTitle());
        blog.setContent(blogDTO.getContent());
        blog.setImage(blogDTO.getImage());
        return blog;
    }

    public static BlogDTO toBlogDto(Blog blog) {
        if (blog == null) {
            return null;
        }

        BlogDTO blogDTO = new BlogDTO();
        blogDTO.setId(blog.getId());
        blogDTO.setTitle(blog.getTitle());
        blogDTO.setContent(blog.getContent());
        blogDTO.setImage(blog.getImage());
        return blogDTO;
    }
}

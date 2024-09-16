package com.nasr.twafq.blog.dto;

import com.nasr.twafq.blog.entity.Blog;
import com.nasr.twafq.blog.entity.Link;
import com.nasr.twafq.blog.entity.Sentence;
import java.util.stream.Collectors;
import jakarta.validation.Valid;

public class BlogMapper {

    public static Blog toBlog(@Valid BlogDTO blogDTO) {
        if (blogDTO == null) {
            return null;
        }

        Blog blog = new Blog();
        blog.setId(blogDTO.getId());
        blog.setTitle(blogDTO.getTitle());
        blog.setDate(blogDTO.getDate());
        blog.setImage(blogDTO.getImage());
        blog.setSentences(blogDTO.getSentences().stream()
                .map(BlogMapper::toSentence)
                .collect(Collectors.toList()));
        return blog;
    }

    public static BlogDTO toBlogDto(Blog blog) {
        if (blog == null) {
            return null;
        }

        BlogDTO blogDTO = new BlogDTO();
        blogDTO.setId(blog.getId());
        blogDTO.setTitle(blog.getTitle());
        blogDTO.setDate(blog.getDate());
        blogDTO.setImage(blog.getImage());
        blogDTO.setSentences(blog.getSentences().stream()
                .map(BlogMapper::toSentenceDTO)
                .collect(Collectors.toList()));
        return blogDTO;
    }

    public static Sentence toSentence(SentenceDTO sentenceDTO) {
        if (sentenceDTO == null) {
            return null;
        }
        return new Sentence(sentenceDTO.getTitle(), sentenceDTO.getSentence(), toLink(sentenceDTO.getLink()));
    }

    public static SentenceDTO toSentenceDTO(Sentence sentence) {
        if (sentence == null) {
            return null;
        }
        return new SentenceDTO(sentence.getTitle(), sentence.getSentence(), toLinkDTO(sentence.getLink()));
    }

    public static Link toLink(LinkDTO linkDTO) {
        if (linkDTO == null) {
            return null;
        }
        return new Link(linkDTO.getHref(), linkDTO.getText());
    }

    public static LinkDTO toLinkDTO(Link link) {
        if (link == null) {
            return null;
        }
        return new LinkDTO(link.getHref(), link.getText());
    }
}

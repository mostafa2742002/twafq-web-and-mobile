package com.nasr.twafq.blog.repo;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nasr.twafq.blog.entity.Blog;


@Repository
public interface BlogRepository extends MongoRepository<Blog, String> {

    Optional<Blog> findByTitle(String title);

    Page<Blog> findAllByOrderByViewsDesc(PageRequest of);

}

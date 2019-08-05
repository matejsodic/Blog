package com.matej.blog.PostRepository;

import com.matej.blog.PostEntity.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepositoryImpl extends CrudRepository<Post, Integer> {
}

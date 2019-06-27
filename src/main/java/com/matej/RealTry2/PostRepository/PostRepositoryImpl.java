package com.matej.RealTry2.PostRepository;

import com.matej.RealTry2.PostEntity.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepositoryImpl extends CrudRepository<Post, Integer> {
}

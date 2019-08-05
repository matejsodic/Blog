package com.matej.blog.ImageRepository;

import com.matej.blog.PostEntity.Image;
import org.springframework.data.repository.CrudRepository;

public interface ImageRepositoryImpl extends CrudRepository<Image, Integer> {
}

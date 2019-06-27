package com.matej.RealTry2.ImageRepository;

import com.matej.RealTry2.PostEntity.Image;
import org.springframework.data.repository.CrudRepository;

public interface ImageRepositoryImpl extends CrudRepository<Image, Integer> {
}

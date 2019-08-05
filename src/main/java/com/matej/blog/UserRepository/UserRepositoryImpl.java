package com.matej.blog.UserRepository;

import com.matej.blog.UserEntity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepositoryImpl extends CrudRepository<User, Integer> {
    User findByUsername(String username);
}

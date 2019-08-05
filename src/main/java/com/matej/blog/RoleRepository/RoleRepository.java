package com.matej.blog.RoleRepository;

import com.matej.blog.UserEntity.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Integer> {

}

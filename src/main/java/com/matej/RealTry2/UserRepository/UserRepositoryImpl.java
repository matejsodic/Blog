package com.matej.RealTry2.UserRepository;

import com.matej.RealTry2.UserEntity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

//public interface UserRepositoryImpl {
//     List<User> getAllUsers();
//     void addUser(User user);
//     void deleteUser(int id);
//     void editUser(int id);
//}

public interface UserRepositoryImpl extends CrudRepository<User, Integer> {
    User findByUsername(String username);
}

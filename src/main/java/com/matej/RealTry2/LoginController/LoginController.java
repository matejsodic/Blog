//package com.matej.RealTry2.LoginController;
//
//import com.matej.RealTry2.PostRepository.PostRepositoryImpl;
//import com.matej.RealTry2.UserEntity.User;
//import com.matej.RealTry2.UserRepository.UserRepositoryImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//
//@Controller
//public class LoginController {
//
//    @Autowired
//    UserRepositoryImpl userRepository;
//
//    @Autowired
//    PostRepositoryImpl postRepository;
//
//    @Autowired
//    PasswordEncoder passwordEncoder;
//
//    @GetMapping("/admin3000")
//    public String showLoginPage() {
//        return "login";
//    }
//}

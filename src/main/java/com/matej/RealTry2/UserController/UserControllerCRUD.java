package com.matej.RealTry2.UserController;

import com.matej.RealTry2.PostEntity.Post;
import com.matej.RealTry2.PostRepository.PostRepositoryImpl;
import com.matej.RealTry2.RoleRepository.RoleRepository;
import com.matej.RealTry2.UserEntity.Role;
import com.matej.RealTry2.UserEntity.User;
import com.matej.RealTry2.UserRepository.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.*;

@Controller
public class UserControllerCRUD {

    @Autowired
    UserRepositoryImpl userRepository;

    @Autowired
    PostRepositoryImpl postRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    @GetMapping("/administration")
    public String showUsers(Model model, User user, Principal principal) {
        List<User> allUsers = new ArrayList<>();
        userRepository.findAll().forEach(allUsers::add);
        model.addAttribute("users", allUsers);
        List<Post> postList = new ArrayList<>();
        postRepository.findAll().forEach(postList::add);
        model.addAttribute("posts", postList);
        model.addAttribute("user", user);

        model.addAttribute("name", principal.getName());
        return "userAdministration";
    }

    @PostMapping("/administration/userform")
    public String addUser(User user, @RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("email") String email) {
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);
        userRepository.save(user);
        return "redirect:/administration";
    }

    @GetMapping("/administration/userform")
    public String userForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "editUser";
    }

    @GetMapping("/administration/edituser/{id}")
    public String edit(@PathVariable("id") int id, ModelMap modelmap) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        modelmap.addAttribute("user", user);
        return "editUser";
    }

    @PostMapping("/administration/edituser/{id}")
    public String editUser(@PathVariable("id") int id, @ModelAttribute("newUser") User newUser, @RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("email") String email) {
        User oldUser = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
//        newUser.setUsername(username);
//        newUser.setPassword(password);
//        newUser.setEmail(email);
        oldUser.setUsername(username);
        oldUser.setPassword(passwordEncoder.encode(password));
        oldUser.setEmail(email);
        userRepository.save(oldUser);
        return "redirect:/administration";
    }

    @GetMapping("/administration/delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepository.delete(user);
        return "redirect:/administration";
    }

    @GetMapping("/register")
    public String loadRegisterPage() {
        return "register";
    }

    @PostMapping("/register")
    public String performRegistration(User user, @RequestParam("username") String username, @RequestParam("email") String email, @RequestParam("password") String password) {
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setActive(true);

        Role role = new Role();
        Set<Role> roles = new HashSet<>();
        role.setRole("USER");
        roles.add(role);
        user.setRoles(roles);

        System.out.println(user.getId() + " " + user.getUsername() + " " + user.getEmail() + " " + user.getPassword() + " " + user.getAuthorities() + " " + user.getRoles() + " " + user.isActive());
        userRepository.save(user);
        return "redirect:/admin3000";
    }
}

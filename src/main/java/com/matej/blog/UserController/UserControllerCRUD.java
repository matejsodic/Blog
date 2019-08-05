package com.matej.blog.UserController;

import com.matej.blog.PostEntity.Post;
import com.matej.blog.PostRepository.PostRepositoryImpl;
import com.matej.blog.RoleRepository.RoleRepository;
import com.matej.blog.UserEntity.Role;
import com.matej.blog.UserEntity.User;
import com.matej.blog.UserRepository.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
        List<Post> postList = new ArrayList<>();
        postRepository.findAll().forEach(postList::add);
        model.addAttribute("users", allUsers);
        model.addAttribute("posts", postList);
        model.addAttribute("user", user);
        model.addAttribute("name", principal.getName());
        return "userAdministration";
    }

    @GetMapping("/administration/userform")
    public String userForm(Model model) {
        User user = new User();
        List<Role> roles = (List<Role>) roleRepository.findAll();
        model.addAttribute("user", user);
        model.addAttribute("roles", roles);
        return "editUser";
    }

    @PostMapping("/administration/userform")
    public String addUser(User user, @RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("email") String email, @RequestParam("roleName") Integer roleName, Model model) {
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);
        Role role = roleRepository.findById(roleName).orElseThrow(() -> new IllegalArgumentException("Invalid role id"));
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        model.addAttribute("roles", roles);
        userRepository.save(user);
        return "redirect:/administration";
    }

    @GetMapping("/administration/edituser/{id}")
    public String edit(@PathVariable("id") int id, ModelMap modelmap) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        List<Role> roles = (List<Role>) roleRepository.findAll();
        modelmap.addAttribute("roles", roles);
        modelmap.addAttribute("user", user);
        return "editUser";
    }

    @PostMapping("/administration/edituser/{id}")
    public String editUser(@PathVariable("id") int id, @RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("email") String email, @RequestParam("roleName") Integer roleName) {
        User oldUser = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user d:" + id));
        oldUser.setUsername(username);
        oldUser.setPassword(passwordEncoder.encode(password));
        oldUser.setEmail(email);
        Role role = roleRepository.findById(roleName).orElseThrow(() -> new IllegalArgumentException("Invalid role id"));
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        oldUser.setActive(true);
        oldUser.setRoles(roles);
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
    public String performRegistration(@Valid User user, @RequestParam("username") String username, @RequestParam("email") String email, @RequestParam("password") String password) {
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setActive(true);
        Role role = roleRepository.findById(2).orElseThrow(() -> new IllegalArgumentException("Invalid id "));
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        userRepository.save(user);
        return "redirect:/admin3000";
    }
}

package com.matej.blog.PostController;

import com.matej.blog.ImageRepository.ImageRepositoryImpl;
import com.matej.blog.PostEntity.Image;
import com.matej.blog.PostEntity.Post;
import com.matej.blog.PostRepository.PostRepositoryImpl;
import com.matej.blog.UserEntity.User;
import com.matej.blog.UserRepository.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

@Controller
public class PostController {
    private static final String UPLOAD_FOLDER = "C:/images/";

    @Autowired
    PostRepositoryImpl postRepository;

    @Autowired
    UserRepositoryImpl userRepository;

    @Autowired
    ImageRepositoryImpl imageRepository;

    @GetMapping("/")
    public String showAllPosts(Model model) {
        List<Post> postList = new ArrayList<>();
        postRepository.findAll().forEach(postList::add);
        model.addAttribute("posts", postList);
        return "index";
    }

    @PostMapping(value = "/administration/postform", consumes = {"multipart/form-data"})
    public String addPost(Post post, @RequestParam("postname") String postname, @RequestParam("datecreated") @DateTimeFormat(pattern = "dd-MM-yyyy") Date datecreated, @RequestParam("postpreview") String postpreview, @RequestParam("postimagesname") MultipartFile[] postimages) throws IOException {
        post.setPostname(postname);
        post.setDateCreated(datecreated);
        post.setPostpreview(postpreview);
        StringBuilder fileNames = new StringBuilder();
        Set<Image> hehe = new HashSet<>();

        for (MultipartFile postImage : postimages) {
            Image image = new Image();
            Path fileNameAndPath = Paths.get(UPLOAD_FOLDER, postImage.getOriginalFilename());
            image.setImagefilepath(fileNameAndPath.toString());
            hehe.add(image);
            fileNames.append(postImage.getOriginalFilename());
            Files.write(fileNameAndPath, postImage.getBytes());
        }
        post.setImages(hehe);
        postRepository.save(post);
        return "redirect:/administration";
    }

    @GetMapping("/administration/postform")
    public String userForm(Model model) {
        Post post = new Post();
        Date date = new Date();
        post.setDateCreated(date);
        model.addAttribute("post", post);
        return "editPost";
    }

    @GetMapping("/administration/editpost/{id}")
    public String editPost(@PathVariable("id") int id, ModelMap modelmap) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid post Id:" + id));
        Date date = new Date();
        post.setDateModified(date);
        List<Image> images = (List<Image>) imageRepository.findAll();
        modelmap.addAttribute("post", post);
        modelmap.addAttribute("images", images);
        return "editPost";
    }

    @PostMapping("/administration/editpost/{id}")
    public String editUser(@PathVariable("id") int id, @ModelAttribute("newUser") Post newPost, @RequestParam("postname") String postname, @RequestParam("postpreview") String postpreview, @RequestParam("datemodified") @DateTimeFormat(pattern = "dd-MM-yyyy") Date datemodified) {
        Post oldPost = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid post Id:" + id));
        oldPost.setPostname(postname);
        oldPost.setPostpreview(postpreview);
        oldPost.setDateModified(datemodified);
        postRepository.save(oldPost);
        return "redirect:/administration";
    }

    @GetMapping("/administration/deletepost/{id}")
    public String deletePost(@PathVariable("id") int id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid post Id:" + id));
        postRepository.delete(post);
        return "redirect:/administration";
    }

    @GetMapping("/post/readmore/{id}")
    public String postView(@PathVariable("id") int id, ModelMap modelMap) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid post Id:" + id));
        User user = post.getUser();
        Set<Image> images2 = new HashSet<>();
        modelMap.addAttribute("post", post);
        modelMap.addAttribute("user", user);
        modelMap.addAttribute("images", images2);
        return "postview";
    }

    @GetMapping("/administration/editpost/deleteimage/{id}")
    public String deleteImage(@PathVariable("id") int id) {
        Image image = imageRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid image Id:" + id));
        imageRepository.delete(image);
        return "redirect:/administration/editpost/{id}";
    }
}
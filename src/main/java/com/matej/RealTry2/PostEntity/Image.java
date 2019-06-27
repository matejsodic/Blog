package com.matej.RealTry2.PostEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "post_images")
public class Image {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "image_file_path")
    private String imagefilepath;

    @ManyToMany(mappedBy = "images")
    Set<Post> posts = new HashSet<>();

    public Image() {}

    public Image(String imagefilepath, Set<Post> posts) {
        this.imagefilepath = imagefilepath;
        this.posts = posts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagefilepath() {
        return imagefilepath;
    }

    public void setImagefilepath(String imagefilepath) {
        this.imagefilepath = imagefilepath;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }
}

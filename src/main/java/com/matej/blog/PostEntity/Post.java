package com.matej.blog.PostEntity;

import com.matej.blog.UserEntity.User;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @NotEmpty
    @Size(min=2, message="Postname must be at least 2 characters long")
    @Column(name = "postname")
    private String postname;


    @Column(name = "date_created")
    private Date dateCreated;

    @Column(name = "date_modified")
    private Date dateModified;

    @NotEmpty
    @Size(min=4, message="Postpreview must be at least 4 characters long")
    @Column(name = "postpreview")
    private String postpreview;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "post_images", joinColumns = {@JoinColumn(name = "post_id")}, inverseJoinColumns = {@JoinColumn(name = "images_id")})
    private Set<Image> images = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Post() {
    }

    public Post(int id, String postname, Date datecreated, Date datemodified, String postpreview) {
        this.id = id;
        this.postname = postname;
        this.dateCreated = datecreated;
        this.dateModified = datemodified;
        this.postpreview = postpreview;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPostname() {
        return postname;
    }

    public void setPostname(String postname) {
        this.postname = postname;
    }

    public String getPostpreview() {
        return postpreview;
    }

    public void setPostpreview(String postpreview) {
        this.postpreview = postpreview;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

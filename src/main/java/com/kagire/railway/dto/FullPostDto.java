package com.kagire.railway.dto;

import com.kagire.railway.entities.Post;
import com.kagire.railway.entities.PostImage;

import java.util.List;

public class FullPostDto {

    String username;
    String email;
    private Post post;
    private List<PostImage> postImages;

    public FullPostDto(String username, String email, Post post, List<PostImage> postImages) {
        this.username = username;
        this.email = email;
        this.post = post;
        this.postImages = postImages;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public List<PostImage> getPostImages() {
        return postImages;
    }

    public void setPostImages(List<PostImage> postImages) {
        this.postImages = postImages;
    }
}

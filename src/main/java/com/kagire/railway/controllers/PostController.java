package com.kagire.railway.controllers;

import com.kagire.railway.dto.FullPostDto;
import com.kagire.railway.entities.CustomUserDetails;
import com.kagire.railway.exceptions.PostException;
import com.kagire.railway.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("posts")
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping("create")
    public final ResponseEntity<String> createPost(@NotNull @RequestParam("images") MultipartFile[] multipartFiles,
                                           @RequestParam("title") String title,
                                           @RequestParam("text") String text) throws PostException {

        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            long postId = postService.savePost(multipartFiles, title, text, ((CustomUserDetails)principal).getEmail());
            return new ResponseEntity<>("Post created with id " + postId, HttpStatus.CREATED);
        } catch (Exception e){
            e.printStackTrace();
            throw new PostException("Error occurred while publishing post (" + e.getClass() + ")");
        }
    }

    @GetMapping
    public ResponseEntity<List<FullPostDto>> getPosts(){
        List<FullPostDto> postDtoList = postService.getFullPosts();
        if (postDtoList.isEmpty()) throw new PostException("No posts yet");
        return new ResponseEntity<>(postDtoList, HttpStatus.OK);
    }
}

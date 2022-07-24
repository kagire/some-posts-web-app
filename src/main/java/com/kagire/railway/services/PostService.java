package com.kagire.railway.services;

import com.kagire.railway.dto.FullPostDto;
import com.kagire.railway.entities.CustomUser;
import com.kagire.railway.entities.Post;
import com.kagire.railway.entities.PostImage;
import com.kagire.railway.repositories.CustomUserRepository;
import com.kagire.railway.repositories.PostImageRepository;
import com.kagire.railway.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class PostService {

    @Autowired
    CustomUserRepository customUserRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    PostImageRepository postImageRepository;

    @Transactional
    public long savePost(MultipartFile[] multipartFiles, String title, String text, String email) throws IOException {

        CustomUser user = customUserRepository.findUserByEmail(email);
        List<String> images = encodeToBase64(multipartFiles);
        List<PostImage> postImages = new ArrayList<>();

        Post post = postRepository.save(new Post(user.getId(), title, text));

        for(String image : images){
           postImages.add(new PostImage(post.getId(), image));
        }

        postImageRepository.saveAll(postImages);
        return post.getId();
    }

    private List<String> encodeToBase64(MultipartFile[] multipartFiles) throws IOException {
        List<String> images = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            images.add(Base64.getEncoder().encodeToString(multipartFile.getBytes()));
        }
        return images;
    }



    public List<FullPostDto> getFullPosts(){
        Iterable<Post> posts = postRepository.findAll();
        List<FullPostDto> fullPostDtoList = new ArrayList<>();
        posts.forEach(post -> {
            CustomUser user = customUserRepository.findUserById(post.getAuthor());
            fullPostDtoList.add(new FullPostDto(user.getUsername(), user.getEmail(), post,
                    postImageRepository.findAllByPostId(post.getId())));
        });
        return fullPostDtoList;
    }
}

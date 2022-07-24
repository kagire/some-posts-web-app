package com.kagire.railway.repositories;

import com.kagire.railway.entities.PostImage;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostImageRepository  extends CrudRepository<PostImage, Long> {

    List<PostImage> findAllByPostId(long postId);
}

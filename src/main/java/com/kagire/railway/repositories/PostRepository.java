package com.kagire.railway.repositories;

import com.kagire.railway.entities.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository  extends CrudRepository<Post, Long> { }

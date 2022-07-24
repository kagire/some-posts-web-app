package com.kagire.railway.repositories;

import com.kagire.railway.entities.CustomUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomUserRepository extends CrudRepository<CustomUser, Long> {

    @Query(value = "SELECT * FROM users WHERE username = ?1", nativeQuery = true)
    CustomUser getUserByUsername(String username);

    @Query(value = "SELECT * FROM users WHERE email = ?1", nativeQuery = true)
    CustomUser findUserByEmail(String email);

    @Query(value = "SELECT * FROM users WHERE id = ?1", nativeQuery = true)
    CustomUser findUserById(long id);
}

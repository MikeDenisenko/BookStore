package com.mike.repository;

import com.mike.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.name = :name")
    User findByName(@Param("name") String name);

    @Query("select u from User u where u.id = :id")
    User find(@Param("id") int id);
}

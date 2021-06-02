package com.mike.repository;

import com.mike.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    @Query("select r from Role r where r.title = :title")
    Role find(@Param("title") String title);

    @Query("select r from Role r where r.id = :id")
    Role find(@Param("id") int id);
}

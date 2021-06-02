package com.mike.service;

import com.mike.model.User;

import java.util.List;

public interface UserService {

    User save(User user);

    User find(int id);

    User find(String name);

    void delete(User user);

    User update(User user);

    List<User> findAll();

}

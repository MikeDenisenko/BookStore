package com.mike.service;

import com.mike.dto.UserDto;
import com.mike.model.Role;
import com.mike.model.User;
import com.mike.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl {

    private UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.saveAndFlush(user);
        return savedUser;
    }

    public User find(int id) {
        return userRepository.find(id);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public User update(User user) {
        return userRepository.saveAndFlush(user);
    }

    public void encodePasswordUpdate(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.saveAndFlush(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void delete(int id) {
        userRepository.deleteById(id);
    }

    public User findUserByName(String name) {
        return userRepository.findByName(name);
    }

    public void encodeAll() {
        for(User user : findAll()) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userRepository.saveAndFlush(user);
        }
    }

    public User getUserFromDto(UserDto userDto) {

        Role role = new Role();
        role.setTitle(userDto.getRole());
        role.setId((role.getTitle().equals("user"))? 1 : 2);
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setBirthday(userDto.getBirthday());
        user.setGender(userDto.getGender());
        user.setRole(role);
        return user;
    }

    public UserDto toUserDto(User user) {

        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setPassword(user.getPassword());
        userDto.setEmail(user.getEmail());
        userDto.setBirthday(user.getBirthday());
        userDto.setGender(user.getGender());
        userDto.setRole(user.getRole().getTitle());
        return userDto;
    }
}

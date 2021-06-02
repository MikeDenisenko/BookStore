package com.mike.controller;


import com.mike.dto.UserDto;
import com.mike.model.User;
import com.mike.service.UserServiceImpl;
import com.mike.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
public class AdminRestController {

    private final UserServiceImpl userService;

    private final UserValidator userValidator;

    @Autowired
    private AdminRestController(UserServiceImpl userService, UserValidator userValidator){
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @GetMapping(value = "/api/users",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> findAll() {
        List<User> users = userService.findAll();
        return users;
    }

    @GetMapping(value = "/api/users/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public User find(@PathVariable("id") int id) {
        User user = userService.find(id);
        return user;
    }

    @PostMapping(value = "/api/users",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public int save(@Valid @RequestBody UserDto userDto, BindingResult bindingResult) {
        userDto.setUniqueNameValidation(true);
        userValidator.validate(userDto, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(BAD_REQUEST, "Sorry, data isn`t correct");
        }
        User user = userService.save(userDto.toUser());
        return user.getId();
    }

    @DeleteMapping(value = "/api/users",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public String delete(@RequestBody User user) {
        if(userService.find(user.getId()) != null) {
            userService.delete(user);
            return ("user " + user.getName() + " is deleted");
        }
        throw new ResponseStatusException(NOT_FOUND, "Sorry, user not found");
    }

    @PostMapping(value = "/api/captchaValidation")
    public boolean restValidateCaptcha(@RequestBody String captcha, HttpSession session) {
        return captcha.equals(session.getAttribute("captcha_security").toString());
    }

    @PostMapping(value = "/api/nameValidation")
    public boolean restValidateName(@RequestBody String name) {
        return (userService.findUserByName(name) == null);
    }
}


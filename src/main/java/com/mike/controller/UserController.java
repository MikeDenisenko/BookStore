package com.mike.controller;

import com.mike.model.Book;
import com.mike.service.BookService;
import com.mike.service.SecurityServiceImpl;
import com.mike.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {

    private SecurityServiceImpl securityService;
    private BookService bookService;
    private UserServiceImpl userService;

    @Autowired
    public UserController(SecurityServiceImpl securityService, BookService bookService, UserServiceImpl userService) {
        this.securityService = securityService;
        this.bookService = bookService;
        this.userService = userService;
    }

    @GetMapping(value = "/user")
    public ModelAndView bookStore() {
        List<Book> books = bookService.getVisibleBooks();
        ModelAndView modelAndView = new ModelAndView("MainPage");
        modelAndView.addObject("currentUser", securityService.findLoggedInUsername());
        modelAndView.addObject("books", books);
        return modelAndView;
    }

    @GetMapping(value = "/user/profile")
    public ModelAndView userProfile() {
        ModelAndView modelAndView = new ModelAndView("ProfilePage");
        modelAndView.addObject("currentUser", securityService.findLoggedInUsername());
        modelAndView.addObject("user", userService.findUserByName(securityService.findLoggedInUsername()));
        return modelAndView;
    }
}

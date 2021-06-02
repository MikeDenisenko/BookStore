package com.mike.controller;

import com.mike.dto.UserDto;
import com.mike.model.Book;
import com.mike.service.BookService;
import com.mike.service.SecurityServiceImpl;
import com.mike.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class BookController {

    private final BookService bookService;

    private final UserValidator userValidator;

    private final SecurityServiceImpl securityService;

    @Autowired
    public BookController(BookService bookService, UserValidator userValidator, SecurityServiceImpl securityService){
        this.bookService = bookService;
        this.userValidator = userValidator;
        this.securityService = securityService;
    }

    @GetMapping(value = "/admin/books")
    public ModelAndView bookList() {
        ModelAndView modelAndView = new ModelAndView();
        List<Book> books = bookService.findAll();
        modelAndView.addObject("currentUser", securityService.findLoggedInUsername());
        modelAndView.addObject("books", books);
        modelAndView.setViewName("BookListPage");
        return modelAndView;
    }

    @DeleteMapping(value = "/admin/books/{id}")
    public ModelAndView deleteUser(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/books");
        bookService.delete(id);
        return modelAndView;
    }

    @PostMapping(value = "/admin/books/{id}")
    public ModelAndView getEditBookForm(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        Book book = bookService.find(id);
        modelAndView.addObject("book", book);
        modelAndView.setViewName("EditBook");
        return modelAndView;
    }

    @PatchMapping(value = "/admin/books/edit")
    public ModelAndView editUser(@Valid @ModelAttribute ("user") UserDto user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/books");
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("EditUser");
            return modelAndView;
        }

        modelAndView.addObject("currentUser", securityService.findLoggedInUsername());
        return modelAndView;
    }

    @GetMapping(value = "/admin/books/add")
    public ModelAndView addUserRequestForm () {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("book", new Book());
        modelAndView.setViewName("CreateBook");
        return modelAndView;
    }

    @PostMapping(value = "/admin/books/add")
    public ModelAndView addUser(@Valid @ModelAttribute("user") UserDto user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/books");
       // bookValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("CreateUser");
            return modelAndView;
        }
        modelAndView.addObject("currentUser", securityService.findLoggedInUsername());
      //  bookService.save(bookService.getUserFromDto(user));
        return modelAndView;
    }
}

